package org.fade.springclouddemo.service.impl;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * 回退Service实现类
 *
 * @author fade
 * @date 2021/09/17
 */
@Service
public class FallbackServiceImpl implements PaymentService {

    @Override
    public CommonResult<String> nacos() {
        return new CommonResult<>(444, "test sentinel feign");
    }

}
