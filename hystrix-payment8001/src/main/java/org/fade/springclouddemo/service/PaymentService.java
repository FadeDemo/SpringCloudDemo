package org.fade.springclouddemo.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public CommonResult<String> infoOk(Integer id) {
        return new CommonResult<>(200, Thread.currentThread().getName() + " info ok, id = " + id);
    }

    @HystrixCommand(fallbackMethod = "infoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public CommonResult<String> infoTimeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<>(444, Thread.currentThread().getName() + " info timeout, id = " + id);
    }

    public CommonResult<String> infoTimeoutHandler(Integer id) {
        return new CommonResult<>(444, "请稍后再试");
    }

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public CommonResult<String> circuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        return new CommonResult<>(200, "调用成功，流水号为：" + IdUtil.simpleUUID());
    }

    public CommonResult<String> fallback(Integer id) {
        return new CommonResult<>(500, "id 不能为负数");
    }

}
