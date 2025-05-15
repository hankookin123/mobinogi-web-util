package com.mobinogi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MobinogiWebUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobinogiWebUtilApplication.class, args);
	}

}
