package com.nexflare.testhiber;

import com.nexflare.testhiber.requestModel.Blog.CreateBlogRequestObject;
import com.nexflare.testhiber.service.BaseHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan( basePackages = {"com.nexflare.testhiber.pojo"} )
public class TesthiberApplication {

	public static void main(String[] args) {

		SpringApplication.run(TesthiberApplication.class, args);
	}

}
