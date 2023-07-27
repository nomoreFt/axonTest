package com.example.axontest.handler;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventHandler {

    /**
     * Receive all events and log them.
     */
    @org.axonframework.eventhandling.EventHandler
    public void on(EventMessage<?> event) {
        log.info("Received event: " + event.getPayload());
    }
}
