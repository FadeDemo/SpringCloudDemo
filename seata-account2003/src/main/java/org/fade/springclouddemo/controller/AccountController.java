package org.fade.springclouddemo.controller;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户Controller
 *
 * @author fade
 * @date 2021/09/20
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource(name = "accountServiceImpl")
    private AccountService accountService;

    @PostMapping("/decrease")
    public CommonResult<String> decrease(Long userId, BigDecimal used) {
        accountService.decrease(userId, used);
        return new CommonResult<>(200, "扣减金额成功");
    }

}
