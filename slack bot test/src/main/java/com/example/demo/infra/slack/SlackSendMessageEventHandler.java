package com.example.demo.infra.slack;

import com.example.demo.domain.TestEvent;
import com.slack.api.Slack;
import com.slack.api.model.block.*;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.webhook.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.event.TransactionPhase;
//import org.springframework.transaction.event.TransactionalEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Slf4j
@Component
public class SlackSendMessageEventHandler {
    @Value("${slack.webhook.url}")
    String url;

    @Async
    @EventListener(TestEvent.class)
    public void handle(TestEvent testEvent) {
        final Slack slack = Slack.getInstance();
        final List<LayoutBlock> blocks = SlackMessageConverter(testEvent);
        final Payload payload =
                Payload.builder()
                        .text(Integer.toString(testEvent.getNumber()))
                        .username("유저")
                        .iconEmoji(":dog:")
                        .blocks(blocks)
                        .build();
        String responseBody = null;
        try {
            responseBody = slack.send(url, payload).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info(responseBody);
    }

    private List<LayoutBlock> SlackMessageConverter(TestEvent testEvent) {
        List<LayoutBlock> layoutBlocks = new ArrayList<>();
        // 제목
        layoutBlocks.add(HeaderBlock.builder().text(plainText("제목")).build());
        // 구분선
        layoutBlocks.add(new DividerBlock());
        // text
        MarkdownTextObject messageText = MarkdownTextObject.builder()
                .text(Integer.toString(testEvent.getNumber()))
                .build();
        layoutBlocks.add(SectionBlock.builder().text(messageText).build());
        return layoutBlocks;
    }
}
