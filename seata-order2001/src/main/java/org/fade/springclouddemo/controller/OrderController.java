package org.fade.springclouddemo.controller;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.entity.Order;
import org.fade.springclouddemo.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单Controller
 *
 * @author fade
 * @date 2021/09/20
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource(name = "orderServiceImpl")
    private OrderService orderService;

    @PostMapping("/save")
    public CommonResult<String> save(Order order) {
        orderService.save(order);
        return new CommonResult<>(200, "创建订单成功！");
    }

}
