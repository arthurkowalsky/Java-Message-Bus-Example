package com.example.messagebus.application.query;

import com.kov.messagebus.handlers.QueryHandler;
import org.springframework.stereotype.Component;

@Component
class ExampleQueryHandler implements QueryHandler<ExampleQuery, String> {
    @Override
    public String handle(ExampleQuery exampleQuery) {
        return "Some data returned from the query";
    }
}
