package com.example.demo.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping()
    public String Test(){
        testService.sendMessage();
        return "ok";
    }
}
