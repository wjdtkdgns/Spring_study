package com.example.demo.infra.slack;

import com.example.demo.domain.TestEvent;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.event.TransactionPhase;
//import org.springframework.transaction.event.TransactionalEventListener;

import java.io.IOException;

@Slf4j
@Component
public class SlackSendMessageHandler {
    @Value("${slack.webhook.url}")
    String url;

    @Async
    @EventListener(TestEvent.class)
    public void handle(TestEvent testEvent) {
        final Slack slack = Slack.getInstance();
        final Payload payload =
                Payload.builder().text(Integer.toString(testEvent.getNumber())).build();

        String responseBody = null;
        try {
            responseBody = slack.send(url, payload).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info(responseBody);
    }
}
