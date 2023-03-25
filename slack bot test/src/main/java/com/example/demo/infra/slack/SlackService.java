package com.example.demo.infra.slack;

import com.example.demo.common.event.Event;
import com.example.demo.domain.TestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlackService {
    public void SlackTest() {
        Event.raise(new TestEvent(100));
    }
}
