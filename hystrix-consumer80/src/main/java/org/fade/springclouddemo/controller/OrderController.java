package org.fade.springclouddemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "globalHandler")
public class OrderController {

    @Resource
    private OrderService orderService;

    @HystrixCommand
    @GetMapping("/payment/get/ok/{id}")
    CommonResult<String> infoOk(@PathVariable("id") Integer id) {
        int i = 1 / 0;
        return orderService.infoOk(id);
    }

    @HystrixCommand(fallbackMethod = "infoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping("/payment/get/timeout/{id}")
    CommonResult<String> infoTimeout(@PathVariable("id") Integer id) {
        return orderService.infoTimeout(id);
    }

    CommonResult<String> infoTimeoutHandler(@PathVariable("id") Integer id) {
        return new CommonResult<>(444, "请稍后再试");
    }

    CommonResult<String> globalHandler() {
        return new CommonResult<>(444, "系统异常，请稍后再试");
    }

}
