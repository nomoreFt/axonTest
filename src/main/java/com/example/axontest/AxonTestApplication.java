package com.example.axontest;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxonTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonTestApplication.class, args);
    }


    @CommandHandler
    public void CommandHandle(Object obj) {
        // ...
    }

    @EventHandler
    public void EventHandle(Object obj) {
        // ...
    }
}
