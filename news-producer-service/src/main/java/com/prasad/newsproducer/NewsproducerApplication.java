package com.prasad.newsproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsproducerApplication.class, args);
	}

}
