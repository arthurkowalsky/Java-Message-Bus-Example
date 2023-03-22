package com.example.messagebus.application.command;

import com.example.messagebus.application.event.ExampleCommandHandledEvent;
import com.kov.messagebus.MessageBusInterface;
import com.kov.messagebus.handlers.CommandHandler;
import org.springframework.stereotype.Component;

@Component
class ExampleCommandHandler implements CommandHandler<ExampleCommand> {
    private final MessageBusInterface eventBus;
    ExampleCommandHandler(MessageBusInterface eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(ExampleCommand exampleCommand) {
        this.eventBus.dispatch(new ExampleCommandHandledEvent());

        return null;
    }
}
