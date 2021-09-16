package org.fade.springclouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consul")
    public CommonResult<String> paymentConsul() {
        return new CommonResult<>(200,
                "SpringCloud run with Consul on the port " + serverPort);
    }

}
