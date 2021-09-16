package org.fade.springclouddemo.service;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("payment-service")
@RequestMapping("/payment")
public interface FeignPaymentService {

    @GetMapping("/get/{id}")
    CommonResult<Payment> get(@PathVariable("id") Long id);

    @GetMapping("/timeout")
    CommonResult<String> timeout();

}
