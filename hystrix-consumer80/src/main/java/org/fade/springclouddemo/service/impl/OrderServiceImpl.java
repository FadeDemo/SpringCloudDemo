package org.fade.springclouddemo.service.impl;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public CommonResult<String> infoOk(Integer id) {
        return new CommonResult<>(444, "ok 系统异常，请稍后再试");
    }

    @Override
    public CommonResult<String> infoTimeout(Integer id) {
        return new CommonResult<>(444, "timeout 系统异常，请稍后再试");
    }

}
