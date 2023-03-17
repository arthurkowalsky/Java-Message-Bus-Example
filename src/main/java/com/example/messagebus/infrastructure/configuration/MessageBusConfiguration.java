package com.example.messagebus.infrastructure.configuration;

import com.kov.messagebus.MessageBus;
import com.kov.messagebus.MessageBusInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBusConfiguration{
    @Bean
    public MessageBusInterface commandBus() {
        return new MessageBus(getClass().getPackage().getName());
    }

    @Bean
    public MessageBusInterface queryBus() {
        return new MessageBus(getClass().getPackage().getName());
    }
}
