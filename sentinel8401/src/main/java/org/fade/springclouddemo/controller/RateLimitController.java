package org.fade.springclouddemo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.handler.CustomBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult<String> byResource() {
        return new CommonResult<>(200, "byResource");
    }

    public CommonResult<String> handleException(BlockException exception) {
        return new CommonResult<>(444, "dealByResource");
    }

    @GetMapping("/customBlockHandler")
    @SentinelResource(value = "customBlockHandler",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "handleException")
    public CommonResult<String> customBlockHandler() {
        return new CommonResult<>(200, "customBlockHandler");
    }

}
