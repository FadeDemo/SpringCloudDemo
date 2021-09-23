package org.fade.springclouddemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fade.springclouddemo.entity.Order;

/**
 * @author fade
 * @date 2021/09/20
 */
@Mapper
public interface OrderDao {

    /**
     * 新建订单
     * @param order 订单
     * */
    void save(Order order);

    /**
     * 修改订单状态
     * @param userId 用户id
     * @param status 状态
     * */
    void update(@Param("userId") Long userId, @Param("status") Integer status);

}
