package org.fade.springclouddemo.controller;

import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.MessageProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageProviderController {

    @Resource(name = "messageProviderServiceImpl")
    private MessageProviderService messageProviderService;

    @GetMapping("/sendMessage")
    public CommonResult<String> sendMessage() {
        return new CommonResult<>(200, messageProviderService.send());
    }

}
