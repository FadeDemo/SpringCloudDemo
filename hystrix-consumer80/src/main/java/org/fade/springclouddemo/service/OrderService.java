package org.fade.springclouddemo.service;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.impl.OrderServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "hystrix-payment-service", fallback = OrderServiceImpl.class)
// @Service加不加都可以，加了Controller那里可以识别
//@Service
public interface OrderService {

    @GetMapping("/payment/get/ok/{id}")
    CommonResult<String> infoOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/get/timeout/{id}")
    CommonResult<String> infoTimeout(@PathVariable("id") Integer id);

}
