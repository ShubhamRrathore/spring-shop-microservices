package com.example.product_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id){
        return "product-" + id;
    }


    @Value("${custom.message}")
    private String message;

    @GetMapping("/config")
    public String readConfig() {
        return message;
    }





}
