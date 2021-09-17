package org.fade.springclouddemo.service;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.impl.FallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用提供方api的feign接口
 *
 * @author fade
 * @date 2021/09/17
 */
@Service
@FeignClient(value = "provider-payment-service", path = "/payment", fallback = FallbackServiceImpl.class)
public interface PaymentService {

    @GetMapping("/nacos")
    CommonResult<String> nacos();

}
