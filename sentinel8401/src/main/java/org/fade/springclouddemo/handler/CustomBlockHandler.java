package org.fade.springclouddemo.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.fade.springclouddemo.entity.CommonResult;

public class CustomBlockHandler {

    public static CommonResult<String> handleException(BlockException exception) {
        return new CommonResult<>(444, "dealCustomBlockHandler");
    }

    public static CommonResult<String> handleException2(BlockException exception) {
        return new CommonResult<>(444, "dealCustomBlockHandler2");
    }

}
