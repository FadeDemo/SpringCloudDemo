package org.fade.springclouddemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 账户dao
 *
 * @author fade
 * @date 2021/09/20
 */
@Mapper
public interface AccountDao {

    /**
     * 减金额
     * @param userId 用户id
     * @param used 已用金额
     * */
    void decrease(@Param("userId") Long userId, @Param("used") BigDecimal used);

}
