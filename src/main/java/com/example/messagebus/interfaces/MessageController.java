package com.example.messagebus.interfaces;

import com.example.messagebus.application.command.ExampleCommand;
import com.example.messagebus.application.query.ExampleQuery;
import com.kov.messagebus.MessageBusInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageBusInterface commandBus;
    private final MessageBusInterface queryBus;

    public MessageController(MessageBusInterface commandBus, MessageBusInterface queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }


    @GetMapping(path = "/command")
    public void processCommand(@RequestBody String messageContent) {
        commandBus.dispatch(new ExampleCommand(messageContent));
    }

    @GetMapping(path = "/query")
    public String processQuery(@RequestBody String messageContent) {
        String resultFromQuery = queryBus.dispatch(new ExampleQuery());

        return resultFromQuery;
    }
}
