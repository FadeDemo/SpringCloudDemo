package org.fade.springclouddemo.controller;

import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    private static final String SERVICE_ADDRESS = "http://consul-payment-service";

    @GetMapping("/consul")
    public CommonResult<String> paymentInfo() {
        return restTemplate.getForObject(SERVICE_ADDRESS + "/payment/consul", CommonResult.class);
    }

}
