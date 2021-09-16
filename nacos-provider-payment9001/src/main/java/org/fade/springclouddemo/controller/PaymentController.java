package org.fade.springclouddemo.controller;

import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/nacos")
    public CommonResult<String> nacos() {
        return new CommonResult<>(200, "端口号为" + serverPort + "的服务提供者已注册进nacos");
    }

}
