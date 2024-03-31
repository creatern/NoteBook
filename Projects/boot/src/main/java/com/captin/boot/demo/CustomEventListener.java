package com.captin.boot.demo;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener {
    @EventListener
    public void customerEventListner(CustomEvent customEvent) {
        System.out.println("##############################################");
        System.out.println("CustomEvent Source:" + customEvent.getSource());
    }
}
