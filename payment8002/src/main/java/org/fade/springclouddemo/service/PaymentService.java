package org.fade.springclouddemo.service;

import org.fade.springclouddemo.entity.Payment;

public interface PaymentService {

    int save(Payment payment);

    Payment get(Long id);

}
