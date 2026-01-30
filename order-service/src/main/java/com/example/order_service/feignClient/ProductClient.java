package com.example.order_service.feignClient;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
    @Retry(name = "inventoryRetry")
    @GetMapping("/products/{id}")
    String getProduct(@PathVariable("id") Long id);



}
