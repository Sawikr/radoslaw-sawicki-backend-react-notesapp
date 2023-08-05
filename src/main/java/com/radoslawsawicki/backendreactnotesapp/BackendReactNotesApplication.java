package com.radoslawsawicki.backendreactnotesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BackendReactNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendReactNotesApplication.class, args);
    }
}
