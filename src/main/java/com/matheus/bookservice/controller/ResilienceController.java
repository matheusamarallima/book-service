package com.matheus.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "foo bar")
@RestController
@RequestMapping("book-service")
public class ResilienceController {

    private Logger logger = LoggerFactory.getLogger(ResilienceController.class);

    @Operation(summary = "fooo")

    @GetMapping("/foo-bar")
//    @Retry(name = "foo-bar", fallbackMethod = "fallBackMethod")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallBackMethod")
//    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String fooBar(){
        logger.info("Request to foo-bar is received");
//        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);

//        return response.getBody();
        return "Foooo";
    }

    public String fallBackMethod(Exception exception){
        return "fallBackMethod foo-bar!";
    }
}
