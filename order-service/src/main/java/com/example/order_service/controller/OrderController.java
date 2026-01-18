package com.example.order_service.controller;

import com.example.order_service.feignClient.ProductClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/{id}")
    public String createOrder(@PathVariable Long id) {
        String product = productClient.getProduct(id);
        return "Order created for " + product;
    }
}
