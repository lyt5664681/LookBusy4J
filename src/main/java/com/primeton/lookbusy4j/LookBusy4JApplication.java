package com.primeton.lookbusy4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LookBusy4JApplication {

    public static void main(String[] args) {
        SpringApplication.run(LookBusy4JApplication.class, args);
    }

}
