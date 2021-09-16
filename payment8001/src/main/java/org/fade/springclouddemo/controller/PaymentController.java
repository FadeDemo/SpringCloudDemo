package org.fade.springclouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.entity.Payment;
import org.fade.springclouddemo.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource(name = "paymentServiceImpl")
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/save")
    public CommonResult<Integer> savePayment(@RequestBody Payment payment){
        int result = paymentService.save(payment);
        log.info("******插入结果:"+result+"******");
        if (result > 0) {
            return new CommonResult<>(200, "插入成功在payment server:" + serverPort, result);
        }
        return new CommonResult<>(444, "插入失败");
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> savePayment(@PathVariable("id") Long id){
        Payment payment = paymentService.get(id);
        log.info("******查询结果:"+payment+"******");
        if (payment != null) {
            return new CommonResult<>(200, "查询成功在payment server:" + serverPort, payment);
        }
        return new CommonResult<>(444, "记录不存在");
    }

    @GetMapping("/discovery")
    public CommonResult<Void> discover() {
        List<String> services = discoveryClient.getServices();
        log.info(services.toString());
        List<ServiceInstance> instances = discoveryClient.getInstances("payment-service");
        log.info(instances.toString());
        return new CommonResult<>();
    }

    @GetMapping("/timeout")
    public CommonResult<String> timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<>(200, serverPort);
    }

    @GetMapping("/sleuth")
    public CommonResult<String> sleuth() {
        return new CommonResult<>(200, "Sleuth Test");
    }

}
