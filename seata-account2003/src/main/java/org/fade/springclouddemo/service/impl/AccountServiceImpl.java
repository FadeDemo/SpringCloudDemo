package org.fade.springclouddemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.dao.AccountDao;
import org.fade.springclouddemo.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户Service实现类
 *
 * @author fade
 * @date 2021/09/20
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    /**
     * 减金额
     *
     * @param userId 用户id
     * @param used   已用金额
     */
    @Override
    public void decrease(Long userId, BigDecimal used) {
        log.info("账户微服务开始扣减金额");
        accountDao.decrease(userId, used);
        log.info("账户微服务扣减金额结束");
    }

}
