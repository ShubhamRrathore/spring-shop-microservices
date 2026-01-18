package com.example.api_gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class gateWayController {


    @Value("${custom.message}")
    private String message;

    @GetMapping("/config")
    public String readConfig() {
        return message;
    }


}
