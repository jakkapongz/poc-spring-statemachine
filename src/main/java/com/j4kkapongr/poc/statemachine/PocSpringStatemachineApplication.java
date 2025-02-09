package com.j4kkapongr.poc.statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class PocSpringStatemachineApplication {

    private final StateMachine<String, String> stateMachine;

    public PocSpringStatemachineApplication(final StateMachine<String, String> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public static void main(String[] args) {
        SpringApplication.run(PocSpringStatemachineApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        stateMachine.start();

        stateMachine.sendEvent(MessageBuilder.withPayload("CONNECTING").build());
    }
}
