package org.fade.springclouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.entity.Payment;
import org.fade.springclouddemo.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    private static final String PAYMENT_URL = "http://PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource(name = "myLoadBalancer")
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/save",
                payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/get2/{id}")
    public CommonResult getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommonResult(444, "获取失败");
    }

    @GetMapping("/payment/lb")
    public CommonResult getMyLB () {
        List<ServiceInstance> instances = discoveryClient.getInstances("payment-service");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/get/1", CommonResult.class);
    }

    @GetMapping("/sleuth")
    public CommonResult<String> sleuth() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/sleuth", CommonResult.class);
    }

}
