package com.example.messagebus.application.commands;

import com.kov.messagebus.MessageHandler;

import java.util.logging.Logger;

@MessageHandler
public class ExampleCommandHandler {
    public void invoke(ExampleCommand message) {
        Logger.getGlobal().info("Command invoked with content: " + message.content());
    }
}
