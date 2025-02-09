package com.j4kkapongr.poc.statemachine.conf;

import com.j4kkapongr.poc.statemachine.actions.ExtConnectionAction;
import com.j4kkapongr.poc.statemachine.guard.ConnectionGuard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

@Configuration
public class SystemStateMachineConfig {

    @Bean
    public StateMachine<String, String> stateMachine(final ConnectionGuard connectionGuard, final ExtConnectionAction extConnectionAction) throws Exception {

        StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial("STANDBY")
                .state("CONNECTING_TO_EXT")
                .state("LIVE");


        builder.configureTransitions()
                .withExternal()
                .source("STANDBY").target("CONNECTING_TO_EXT").event("CONNECTING").action(extConnectionAction)
                .and()
                .withExternal()
                .source("CONNECTING_TO_EXT").target("LIVE").event("APP_READY").guard(connectionGuard)
                .and()
                .withExternal()
                .source("LIVE").target("STANDBY").event("SHUTDOWN");

        return builder.build();

    }
}
