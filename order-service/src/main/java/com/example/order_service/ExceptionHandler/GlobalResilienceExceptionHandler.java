package com.example.order_service.ExceptionHandler;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalResilienceExceptionHandler {

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<String> handleRateLimit(RequestNotPermitted requestNotPermitted){

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Rate Limit Exceeded: Please wait for the 20s window to reset");
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<String> handleCircuitBreaker(CallNotPermittedException ex){
        return  ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Circuit is OPEN: The downstream service is currently unstable.");
    }

    // 3. Handle Feign Errors (When the service is actually down/missing)
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException ex) {
        // This catches the 503 "No servers available" you are seeing
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("🛠️ Downstream Error: The Product Service is currently down or not registered.");
    }



}
