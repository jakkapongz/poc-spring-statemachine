package com.j4kkapongr.poc.statemachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemStateController {


    @Autowired
    private StateMachine<String, String> stateMachine;

    @PostMapping("/activate")
    public String activate() {
        stateMachine.sendEvent(MessageBuilder.withPayload("CONNECTING").build());

        return "System state is now :" + stateMachine.getState().getId();
    }

    @PostMapping("/deactivate")
    public String deactivate() {
        stateMachine.sendEvent(MessageBuilder.withPayload("SHUTDOWN").build());

        return "System state is now :" + stateMachine.getState().getId();
    }

    @GetMapping("/current-state")
    public String currentState() {
        return "System state is now :" + stateMachine.getState().getId();
    }


}
