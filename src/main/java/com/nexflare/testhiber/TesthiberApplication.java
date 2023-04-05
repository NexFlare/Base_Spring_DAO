package com.nexflare.testhiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"com.nexflare.testhiber.pojo"} )
public class TesthiberApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesthiberApplication.class, args);
	}

}
