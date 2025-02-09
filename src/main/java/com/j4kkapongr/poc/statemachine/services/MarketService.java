package com.j4kkapongr.poc.statemachine.services;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class MarketService {


    private boolean connected;

    public void connect() throws InterruptedException {

        int x = 0;

        while (x < 10)
        {
            Thread.sleep(1000);

            System.out.println("MarketServiceGuard is sleeping : " + x);

            x++;
        }

        this.connected = true;
    }
}
