package com.example.messagebus.infrastructure.configuration;

import com.kov.messagebus.MessageBus;
import com.kov.messagebus.MessageBusInterface;
import com.kov.messagebus.handlers.CommandHandler;
import com.kov.messagebus.handlers.EventHandler;
import com.kov.messagebus.handlers.QueryHandler;
import com.kov.messagebus.middlewares.LoggingMiddleware;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MessageBusConfiguration{
    @Bean
    public MessageBusInterface commandBus(ObjectProvider<CommandHandler> handlers) {
        return MessageBus.create().withHandlers(handlers.stream().toList());
    }

    @Bean
    public MessageBusInterface queryBus(ObjectProvider<QueryHandler> handlers) {
        return MessageBus.create().withHandlers((handlers.stream().toList()));
    }

    @Bean
    public MessageBusInterface eventBus(ObjectProvider<EventHandler> handlers) {
        return MessageBus.create()
                .withMiddlewares(List.of(new LoggingMiddleware()))
                .withAllowNoHandlers(true)
                .withHandlers((handlers.stream().toList()));
    }
}
