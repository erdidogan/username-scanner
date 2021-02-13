package com.erdidogan.apps.usernamesearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UsernameSearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsernameSearchApiApplication.class, args);

    }


}
