package org.fade.springclouddemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fade
 * @date 2021/09/20
 */
@Mapper
public interface StorageDao {

    /**
     * 减库存
     * @param productId 产品id
     * @param used 已用库存
     * */
    void decrease(@Param("productId") Long productId, @Param("used") Integer used);

}
