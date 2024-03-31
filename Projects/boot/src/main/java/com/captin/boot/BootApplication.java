package com.captin.boot;

import com.captin.boot.demo.CustomEvent;
import com.captin.boot.demo.CustomerEventPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);

	}

	@EventListener(ContextClosedEvent.class)
	@Order(2)
	public void closedEventListner() {
		System.out.println("Application is closed");
	}

}
