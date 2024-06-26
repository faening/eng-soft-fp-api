package com.github.faening.eng_soft_fp_api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/greeting")
public class GreetingController {
    private record Greeting(long id, String content) { }
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    ) private Greeting greeting(
        @RequestParam(value = "name", defaultValue = "World") String name
    ) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}