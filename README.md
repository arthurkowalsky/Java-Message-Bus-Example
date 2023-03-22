# Java Message Bus Example

This project demonstrates how to use the MessageBus library with Spring Boot. It shows the integration of Command and Query buses, along with Event handling using the MessageBus.

## Features

- Message handling with a simple and intuitive API
- Easy integration with Spring Boot

## Getting Started

1. Add the Java Message Bus library as a dependency in your `pom.xml`:

```xml
<dependency>
<groupId>com.kov</groupId>
<artifactId>messagebus</artifactId>
<version>2.0.0</version>
</dependency>
```

2. Configure the message buses in your Spring Boot application:

```java

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

```

3. Create messages and handlers by implementing the `handle` method:

```java
// ExampleCommand
public final record ExampleCommand(String content) implements Command {
}

// ExampleQuery
public final class ExampleQuery implements Query {
}

// ExampleCommandHandledEvent
public final class ExampleCommandHandledEvent implements Event {
}
```

```java
// ExampleCommandHandler
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

// ExampleQueryHandler
@Component
class ExampleQueryHandler implements QueryHandler<ExampleQuery, String> {
    @Override
    public String handle(ExampleQuery exampleQuery) {
        return "Some data returned from the query";
    }
}

// ExampleCommandHandledEventHandler
@Component
public class ExampleCommandHandledEventHandler implements EventHandler<ExampleCommandHandledEvent> {
    @Override
    public Void handle(ExampleCommandHandledEvent exampleCommandHandledEvent) {
        return null;
    }
}

```

4. Inject the command and query buses into your controllers or other components:

```java
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
        return queryBus.dispatch(new ExampleQuery());
    }
}
```

5. Use the `dispatch` method on the message bus to process messages:

```java
    commandBus.dispatch(new ExampleCommand(messageContent));

    String result = queryBus.invoke(new ExampleQuery());
```

6. Run your Spring Boot application and enjoy decoupled message handling!

## More Information

For more information about the Java Message Bus library and its features, please refer to the [Java Message Bus GitHub repository](https://github.com/arthurkowalsky/Java-Message-Bus).

## License

This project is licensed under the MIT License. For more information, please refer to the [LICENSE](LICENSE) file.
