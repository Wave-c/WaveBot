package com.wave_bot.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DispatcherApplication {

    public static void main(String[] args)
    {
        System.out.println("App: RUN");
        SpringApplication.run(DispatcherApplication.class, args);
        System.out.println("App: OK");
    }

}
