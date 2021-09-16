package org.fade.springclouddemo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.fade.springclouddemo.entity.CommonResult;
import org.fade.springclouddemo.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class FlowLimitController {

    @Resource(name = "linkServiceImpl")
    private LinkService linkService;

    @GetMapping("/testA")
    public CommonResult<String> testA() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testA");
        return new CommonResult<>(200, "testA");
    }

    @GetMapping("/testB")
    public CommonResult<String> testB() {
        log.info(Thread.currentThread().getName() + " testB......");
        return new CommonResult<>(200, "testB");
    }

    @GetMapping("/testC")
    public CommonResult<String> testC() {
        return new CommonResult<>(200, linkService.test() + "C");
    }

    @GetMapping("/testD")
    public CommonResult<String> testD() {
        return new CommonResult<>(200, linkService.test() + "D");
    }

    @GetMapping("/testE")
    public CommonResult<String> testE() {
        int age = 1 / 0;
        return new CommonResult<>(200, "testE");
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHotKey")
    public CommonResult<String> testHotKey(@RequestParam(value = "p1", required = false) String p1,
                                           @RequestParam(value = "p2", required = false) String p2) {
        return new CommonResult<>(200, "testHotKey");
    }

    public CommonResult<String> dealHotKey(String p1, String p2, BlockException exception) {
        return new CommonResult<>(200, "dealHotKey");
    }

}
