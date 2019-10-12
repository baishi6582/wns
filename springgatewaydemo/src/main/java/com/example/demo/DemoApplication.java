package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//
//		return builder.routes()
//				.route(p -> p
//						.path("/success")
//						.filters(f -> f.addRequestHeader("Hello", "World"))
//						.uri("https://bior.bce.baidu.com/rest/bior/v1/audio/download?sessionId=a1403c55e4e96c6d-4d5a-e100-93a6-12e5&token=4221805D91266151D4136E1B9994EA09"))
//				.build();
//	}

}
