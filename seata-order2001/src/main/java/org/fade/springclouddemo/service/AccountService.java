package org.fade.springclouddemo.service;

import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 账户Service
 *
 * @author fade
 * @date 2021/09/20
 */
@FeignClient(value = "seata-account-service", path = "/account")
public interface AccountService {

    /**
     * 减金额
     * @param userId 用户id
     * @param used 已用金额
     * @return 操作结果
     * */
    @PostMapping("/decrease")
    CommonResult<String> decrease(@RequestParam("userId") Long userId, @RequestParam("used") BigDecimal used);

}
