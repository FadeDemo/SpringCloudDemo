package org.fade.springclouddemo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private PaymentService paymentService;

    private static final String SERVICE_URL = "http://provider-payment-service";

    @Resource
    private RestTemplate restTemplate;

//    @SentinelResource(value = "fallback", fallback = "handleFallback")
//    @SentinelResource(value = "blockHandler", blockHandler = "handleBlockHandler")
//    @SentinelResource(value = "blockHandler",
//            blockHandler = "handleBlockHandler",
//            fallback = "handleFallback")
    @SentinelResource(value = "fallback",
            fallback = "handleFallback",
            exceptionsToIgnore = {RuntimeException.class})
    @GetMapping("/payment/nacos")
    public CommonResult<String> nacos() {
        throw new RuntimeException("test fallback");
//        return restTemplate.getForObject(SERVICE_URL + "/payment/nacos", CommonResult.class);
    }

    public CommonResult<String> handleFallback(Throwable e) {
        return new CommonResult<>(444, "handle fallback: " + e.getMessage());
    }

    public CommonResult<String> handleBlockHandler(BlockException e) {
        return new CommonResult<>(444, "handle blockHandler: " + e.getMessage());
    }

    @GetMapping("/payment/feignNacos")
    public CommonResult<String> feignNacos() {
        return paymentService.nacos();
    }

}
