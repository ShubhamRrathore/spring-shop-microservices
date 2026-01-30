package com.example.order_service.controller;

import com.example.order_service.feignClient.ProductClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;

    }


    @RateLimiter(name = "inventoryRateLimiter")
    @CircuitBreaker(name = "productServiceCB")
    @GetMapping("/{id}")
    public String createOrder(@PathVariable Long id) {

        log.debug("the order id is  {}" , id);
        String product = productClient.getProduct(id);
        return "Order created for " + product;
    }


}
