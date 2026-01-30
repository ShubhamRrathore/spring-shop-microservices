package com.example.order_service.config;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryEventLogger {

    private final RetryRegistry retryRegistry;

    public RetryEventLogger(RetryRegistry retryRegistry) {
        this.retryRegistry = retryRegistry;
    }

    @PostConstruct
    public void registerRetryEvents() {
        Retry retry = retryRegistry.retry("inventoryRetry");

        retry.getEventPublisher()
                .onRetry(event ->
                        System.out.println(
                                "🔁 Retry attempt: " + event.getNumberOfRetryAttempts()
                        )
                )
                .onError(event ->
                        System.out.println(
                                "❌ Retry failed after attempts: " +
                                        event.getNumberOfRetryAttempts()
                        )
                );
    }
}
