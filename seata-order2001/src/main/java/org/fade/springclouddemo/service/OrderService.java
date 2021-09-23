package org.fade.springclouddemo.service;

import org.fade.springclouddemo.entity.Order;

/**
 * 订单Service
 *
 * @author fade
 * @date 2021/09/20
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order 订单
     * */
    void save(Order order);

}
