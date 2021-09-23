package org.fade.springclouddemo.service.impl;

import cn.hutool.core.util.IdUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.dao.OrderDao;
import org.fade.springclouddemo.entity.Order;
import org.fade.springclouddemo.service.AccountService;
import org.fade.springclouddemo.service.OrderService;
import org.fade.springclouddemo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单Service实现类
 *
 * @author fade
 * @date 2021/09/20
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    /**
     * 创建订单
     *
     * @param order 订单
     */
    @GlobalTransactional(rollbackFor = {Throwable.class})
    @Override
    public void save(Order order) {
        log.info("开始新建订单");
        long id = IdUtil.getSnowflake().nextId();
        order.setId(id);
        orderDao.save(order);
        log.info("订单微服务即将调用库存微服务");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("订单微服务调用库存微服务结束");
        log.info("订单微服务即将调用账户微服务");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("订单微服务调用账户微服务结束");
        log.info("开始修改订单状态");
        orderDao.update(order.getUserId(), 0);
        log.info("修改订单状态结束");
        log.info("新建订单结束");
    }

}
