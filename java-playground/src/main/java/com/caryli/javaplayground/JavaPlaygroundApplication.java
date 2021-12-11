package com.caryli.javaplayground;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaPlaygroundApplication {

    private static long SLEEP_LOWER_MS = 100;
    private static long SLEEP_UPPER_MS = 300;


    @RequestMapping("/")
    public String home() {

        long randomSleepMs = SLEEP_LOWER_MS + (long) (new Random().nextFloat() * (SLEEP_UPPER_MS - SLEEP_LOWER_MS));

        // for experiment, throw an exception whenever random sleep ms ends with 1
        if (randomSleepMs % 10 == 1) {
            throw new RuntimeException("hit planned test error with random sleep: " + randomSleepMs);
        }

        try {
			// simulate a request that needs some processing time
            Thread.sleep(randomSleepMs);
        } catch (InterruptedException e) {
            System.err.print("Not expect to get here");
        }

        return "Slept for " + randomSleepMs + "ms";
    }

	public static void main(String[] args) {
		SpringApplication.run(JavaPlaygroundApplication.class, args);
	}

}
