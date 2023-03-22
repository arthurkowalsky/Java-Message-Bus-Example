package com.example.messagebus.application.event;

import com.kov.messagebus.handlers.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ExampleCommandHandledEventHandler implements EventHandler<ExampleCommandHandledEvent> {
    @Override
    public Void handle(ExampleCommandHandledEvent exampleCommandHandledEvent) {
        return null;
    }
}
