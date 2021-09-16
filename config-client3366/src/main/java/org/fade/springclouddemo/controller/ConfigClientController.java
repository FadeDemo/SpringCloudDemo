package org.fade.springclouddemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigClientController {

    @Value("${test}")
    private String test;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok(test);
    }

}
