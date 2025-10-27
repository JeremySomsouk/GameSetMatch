package com.jsomsouk.tennis.kata.GameSetMatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jsomsouk.tennis")
public class GameSetMatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameSetMatchApplication.class, args);
    }
}
