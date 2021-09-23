package org.fade.springclouddemo.service;

/**
 * @author fade
 * @date 2021/09/20
 */
public interface StorageService {

    /**
     * 减库存
     * @param productId 产品id
     * @param used 已用库存
     * */
    void decrease(Long productId, Integer used);

}
