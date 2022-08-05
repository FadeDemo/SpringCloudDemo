package org.fade.springclouddemo.controller;

import org.fade.springclouddemo.XmlConfigProperties;
import org.fade.springclouddemo.entity.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
public class ConfigController {

    private final XmlConfigProperties xmlConfigProperties;

    public ConfigController(XmlConfigProperties xmlConfigProperties) {
        this.xmlConfigProperties = xmlConfigProperties;
        System.out.println("constructor....");
    }

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

    @GetMapping("/config/getXmlConfigProperties")
    public CommonResult<List<XmlConfigProperties.Tran>> getXmlConfigProperties() {
        return new CommonResult<>(200, "success", xmlConfigProperties.getTrans());
    }

}
