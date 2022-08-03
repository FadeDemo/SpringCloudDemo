package org.fade.springclouddemo.controller;

import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;

    @Value("${share}")
    private String shareConfig;

    @GetMapping("/config/info")
    public CommonResult<String> configInfo() {
        return new CommonResult<>(200, configInfo);
    }

    @GetMapping("/config/shareConfig")
    public CommonResult<String> getShareConfig() {
        return new CommonResult<>(200, shareConfig);
    }

}
