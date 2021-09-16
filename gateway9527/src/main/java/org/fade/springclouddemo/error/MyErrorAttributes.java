package org.fade.springclouddemo.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

//@Component
public class MyErrorAttributes implements ErrorAttributes {

    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";

    private HttpStatus determineHttpStatus(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException) error).getStatus();
        }
        return responseStatusAnnotation.getValue("code", HttpStatus.class).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String determineMessage(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        if (error instanceof BindingResult) {
            return error.getMessage();
        }
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException) error).getReason();
        }
        String reason = responseStatusAnnotation.getValue("reason", String.class).orElse("");
        if (StringUtils.hasText(reason)) {
            return reason;
        }
        return (error.getMessage() != null) ? error.getMessage() : "";
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        Throwable error = getError(request);
        MergedAnnotation<ResponseStatus> responseStatusAnnotation = MergedAnnotations
                .from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);
        HttpStatus errorStatus = determineHttpStatus(error, responseStatusAnnotation);
        //永远返回status 为200
        errorAttributes.put("status", 200);
        //抛出的异常code
        errorAttributes.put("code", errorStatus.value());
        //自定义的 异常内容
        errorAttributes.put("message", determineMessage(error, responseStatusAnnotation));
        handleException(errorAttributes, determineException(error), false);
        return errorAttributes;
    }

    private Throwable determineException(Throwable error) {
        if (error instanceof ResponseStatusException) {
            return (error.getCause() != null) ? error.getCause() : error;
        }
        return error;
    }

    private void handleException(Map<String, Object> errorAttributes, Throwable error, boolean includeStackTrace) {
        errorAttributes.put("exception", error.getClass().getName());
        if (includeStackTrace) {
            addStackTrace(errorAttributes, error);
        }
        if (error instanceof BindingResult) {
            BindingResult result = (BindingResult) error;
            if (result.hasErrors()) {
                errorAttributes.put("errors", result.getAllErrors());
            }
        }
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        errorAttributes.put("trace", stackTrace.toString());
    }

    @Override
    public Throwable getError(ServerRequest request) {
        return (Throwable) request.attribute(ERROR_ATTRIBUTE)
                .orElseThrow(() -> new IllegalStateException("Missing exception attribute in ServerWebExchange"));
    }

    @Override
    public void storeErrorInformation(Throwable error, ServerWebExchange exchange) {
        exchange.getAttributes().putIfAbsent(ERROR_ATTRIBUTE, error);
    }

}
