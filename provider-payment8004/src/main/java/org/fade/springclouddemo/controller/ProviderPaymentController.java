package org.fade.springclouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/payment")
public class ProviderPaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/zookeeper")
    public CommonResult<String> paymentZookeeper() {
        return new CommonResult<>(200,
                "SpringCloud run with Zookeeper on the port " + serverPort);
    }

}
