package com.example.messagebus.application.command;

import com.kov.messagebus.messages.Command;

public final record ExampleCommand(String content) implements Command {

}
