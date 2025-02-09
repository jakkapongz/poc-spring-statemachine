package com.j4kkapongr.poc.statemachine.services;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class OrderService {

    private boolean connected;

    public void connect() throws InterruptedException {
        int x = 0;

        while (x < 5)
        {
            Thread.sleep(1000);

            System.out.println("OrderServiceGuard is sleeping : " + x);

            x++;
        }

        this.connected = true;
    }
}
