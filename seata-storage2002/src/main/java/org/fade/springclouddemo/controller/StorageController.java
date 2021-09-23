package org.fade.springclouddemo.controller;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fade
 * @date 2021/09/20
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource(name = "storageServiceImpl")
    private StorageService storageService;

    @PostMapping("/decrease")
    public CommonResult<String> decrease(Long productId, Integer used) {
        storageService.decrease(productId, used);
        return new CommonResult<>(200, "扣减库存成功");
    }

}
