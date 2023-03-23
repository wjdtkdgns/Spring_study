package com.example.demo;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {
    @Value("${slack.token}")
    private String token;

    @Bean
    public MethodsClient getClient() {
        Slack slackClient = Slack.getInstance();
        return slackClient.methods(token);
    }
}
