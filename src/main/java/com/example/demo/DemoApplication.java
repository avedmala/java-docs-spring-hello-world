package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;

@SpringBootApplication
@RestController
public class DemoApplication {
	StatsDClient statsd = new NonBlockingStatsDClientBuilder()
		.prefix("statsd")
		.hostname("localhost")
		.port(8125)
		.build();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	String sayHello() {
		statsd.incrementCounter("page.views");
		return "💜 Java Datadog Self Monitoring 💜";
	}
}
