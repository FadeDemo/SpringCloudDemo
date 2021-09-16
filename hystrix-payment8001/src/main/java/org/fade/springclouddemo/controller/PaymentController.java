package org.fade.springclouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servePort;

    @GetMapping("/get/ok/{id}")
    public CommonResult<String> infoOk(@PathVariable("id") Integer id) {
        return paymentService.infoOk(id);
    }

    @GetMapping("/get/timeout/{id}")
    public CommonResult<String> infoTimeout(@PathVariable("id") Integer id) {
        return paymentService.infoTimeout(id);
    }

    @GetMapping("/get/circuitBreaker/{id}")
    public CommonResult<String> circuitBreaker(@PathVariable("id") Integer id) {
        return paymentService.circuitBreaker(id);
    }

}
