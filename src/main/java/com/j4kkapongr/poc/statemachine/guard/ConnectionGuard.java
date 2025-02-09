package com.j4kkapongr.poc.statemachine.guard;

import com.j4kkapongr.poc.statemachine.services.MarketService;
import com.j4kkapongr.poc.statemachine.services.OrderService;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class ConnectionGuard implements Guard<String, String> {

    private final OrderService orderService;
    private final MarketService marketService;

    public ConnectionGuard(final OrderService orderService, final MarketService marketService) {
        this.marketService = marketService;
        this.orderService = orderService;
    }

    @Override
    public boolean evaluate(StateContext<String, String> context) {
        return this.marketService.isConnected() && this.orderService.isConnected();
    }
}
