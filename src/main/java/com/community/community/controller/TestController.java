package com.community.community.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test", description = "테스트 API")
public class TestController {

    @GetMapping("/hello")
    @Operation(summary = "Hello World", description = "간단한 테스트 API")
    public Map<String, String> hello() {
        return Map.of(
            "message", "Hello World!",
            "status", "success"
        );
    }

    @GetMapping("/public")
    @Operation(summary = "Public API", description = "인증 없이 접근 가능한 API")
    public Map<String, String> publicApi() {
        return Map.of(
            "message", "This is a public API",
            "timestamp", String.valueOf(System.currentTimeMillis())
        );
    }
}