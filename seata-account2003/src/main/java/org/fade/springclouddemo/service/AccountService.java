package org.fade.springclouddemo.service;

import java.math.BigDecimal;

/**
 * 账户Service
 *
 * @author fade
 * @date 2021/09/20
 */
public interface AccountService {

    /**
     * 减金额
     * @param userId 用户id
     * @param used 已用金额
     * */
    void decrease(Long userId, BigDecimal used);

}
