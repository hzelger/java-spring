package swt5.spring.basics.hello;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreetingClient {
    public static void main() {
        // Create a Spring application context (factory) from the XML configuration file
        try (AbstractApplicationContext appCtx = new ClassPathXmlApplicationContext("swt5/spring/basics/hello/greetingService.xml")) {
            var greeter = appCtx.getBean("greetingService", GreetingService.class);;
            
            greeter.sayHello();
        }
        
    }
}
