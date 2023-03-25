package com.example.demo.domain;

import com.example.demo.infra.slack.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final SlackService slackService;

    public void sendMessage (){
        slackService.SlackTest();
    }
}
