package com.example.messagebus.application.query;

import com.example.messagebus.application.commands.ExampleCommand;
import com.kov.messagebus.MessageHandler;

@MessageHandler
public class ExampleQueryHandler {
    public String invoke(ExampleQuery message) {
        return "Some data returned from query";
    }
}
