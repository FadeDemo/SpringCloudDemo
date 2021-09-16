package org.fade.springclouddemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.fade.springclouddemo.entity.Payment;

@Mapper
public interface PaymentDao {

    int save(Payment payment);

    Payment get(Long id);

}
