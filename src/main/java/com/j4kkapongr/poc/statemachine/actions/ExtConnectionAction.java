package com.j4kkapongr.poc.statemachine.actions;

import com.j4kkapongr.poc.statemachine.services.MarketService;
import com.j4kkapongr.poc.statemachine.services.OrderService;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ExtConnectionAction implements Action<String, String> {

    private final MarketService marketService;

    private final OrderService orderService;

    public ExtConnectionAction(final MarketService marketService, final OrderService orderService) {
        this.marketService = marketService;
        this.orderService = orderService;
    }


    @Override
    public void execute(StateContext<String, String> context) {
        System.out.println(context.getEvent());
        // Run connection tasks asynchronously
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        orderService.connect();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        marketService.connect();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })

        );

        allTasks.thenRun(() -> {
            if (marketService.isConnected() && orderService.isConnected()) {
                context.getStateMachine().sendEvent("APP_READY");
            }
        });

    }
}
