package org.fade.springclouddemo.service.impl;

import org.fade.springclouddemo.dao.PaymentDao;
import org.fade.springclouddemo.entity.Payment;
import org.fade.springclouddemo.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int save(Payment payment) {
        return paymentDao.save(payment);
    }

    @Override
    public Payment get(Long id) {
        return paymentDao.get(id);
    }

}
