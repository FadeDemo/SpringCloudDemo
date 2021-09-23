package org.fade.springclouddemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.dao.StorageDao;
import org.fade.springclouddemo.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fade
 * @date 2021/09/20
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    /**
     * 减库存
     *
     * @param productId 产品id
     * @param used      已用库存
     */
    @Override
    public void decrease(Long productId, Integer used) {
        log.info("库存微服务开始扣减库存");
        // 模拟超时，也可以直接把微服务关闭
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        storageDao.decrease(productId, used);
        log.info("库存微服务扣减库存结束");
    }

}
