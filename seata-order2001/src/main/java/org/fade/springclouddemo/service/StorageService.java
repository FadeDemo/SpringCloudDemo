package org.fade.springclouddemo.service;

import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fade
 * @date 2021/09/20
 */
@FeignClient(value = "seata-storage-service", path = "/storage")
public interface StorageService {

    /**
     * 减库存
     * @param productId 产品id
     * @param used 已用库存
     * @return 操作结果
     * */
    @PostMapping("/decrease")
    CommonResult<String> decrease(@RequestParam("productId") Long productId, @RequestParam("used") Integer used);

}
