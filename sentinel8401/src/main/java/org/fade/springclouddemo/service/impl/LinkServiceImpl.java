package org.fade.springclouddemo.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.fade.springclouddemo.service.LinkService;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {

    @SentinelResource("test")
    @Override
    public String test() {
        return "test";
    }

}
