package org.fade.springclouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.FeignPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    @Resource
    private FeignPaymentService feignPaymentService;

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        return feignPaymentService.get(id);
    }

    @GetMapping("/payment/timeout")
    public CommonResult<String> timeout() {
        return feignPaymentService.timeout();
    }

}
