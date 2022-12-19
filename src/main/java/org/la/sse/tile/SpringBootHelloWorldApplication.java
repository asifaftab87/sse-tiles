package org.la.sse.tile;

import org.la.sse.tile.futur.FutureObject;
import org.la.sse.tile.futur.FutureTwo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootHelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}
	
	@Bean
	public FutureObject futureObject() {
		return new FutureObject();
	}
	
	@Bean
	public FutureTwo futureTwo() {
		return new FutureTwo();
	}
}
