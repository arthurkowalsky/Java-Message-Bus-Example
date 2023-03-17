Java Message Bus Example
This is an example project demonstrating the usage of the Java Message Bus library in a Spring Boot application. The Java Message Bus library provides a simple and effective way to handle messages, such as commands and queries, in a decoupled manner using handlers.

Features
Message handling with a simple and intuitive API
Automatic package scanning for message handlers
Easy integration with Spring Boot
Project Structure
The project is organized into the following packages and classes:

com.example.messagebus: The main Spring Boot application class (JavaMessageBusExampleApplication).
com.example.messagebus.interfaces: Contains the MessageController class, which exposes REST endpoints for processing commands and queries.
com.example.messagebus.infrastructure.configuration: Contains the MessageBusConfiguration class, which defines the MessageBusInterface beans for command and query buses.
com.example.messagebus.application.commands: Contains the ExampleCommand and ExampleCommandHandler classes.
com.example.messagebus.application.query: Contains the ExampleQuery and ExampleQueryHandler classes.
Getting Started
Add the Java Message Bus library as a dependency in your pom.xml:
xml
Copy code
<dependency>
<groupId>com.kov</groupId>
<artifactId>messagebus</artifactId>
<version>1.0.0</version>
</dependency>
Configure the message buses in your Spring Boot application:
java
Copy code
@Configuration
public class MessageBusConfiguration {
@Bean
public MessageBusInterface commandBus() {
return new MessageBus(getClass().getPackage().getName());
}

    @Bean
    public MessageBusInterface queryBus() {
        return new MessageBus(getClass().getPackage().getName());
    }
}
Create message handlers by annotating classes with @MessageHandler and implementing the invoke method:
java
Copy code
@MessageHandler
public class ExampleCommandHandler {
public void invoke(ExampleCommand message) {
// Handle the command
}
}

@MessageHandler
public class ExampleQueryHandler {
public String invoke(ExampleQuery message) {
// Handle the query and return a result
}
}
Inject the command and query buses into your controllers or other components:
java
Copy code
@RestController
@RequestMapping("/messages")
public class MessageController {
private final MessageBusInterface commandBus;
private final MessageBusInterface queryBus;

    public MessageController(MessageBusInterface commandBus, MessageBusInterface queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    // Define endpoints for processing commands and queries
}
Use the invoke method on the message bus to process messages:
java
Copy code
commandBus.invoke(new ExampleCommand("Command content"));
String result = queryBus.invoke(new ExampleQuery());
Run your Spring Boot application and enjoy decoupled message handling!
More Information
For more information about the Java Message Bus library and its features, please refer to the Java Message Bus GitHub repository.