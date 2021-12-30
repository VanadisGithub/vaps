package com.vanadis.future.EventListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestEventListenerController {

    @Autowired
    private MyTestEventPubLisher publisher;

    @GetMapping(value = "/test/testPublishEvent1")
    public void testPublishEvent() {
        publisher.pushListener("我来了！");
        log.info("1");
    }
}