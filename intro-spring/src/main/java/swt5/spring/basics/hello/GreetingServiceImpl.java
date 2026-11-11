package swt5.spring.basics.hello;

import lombok.Setter;

public class GreetingServiceImpl implements GreetingService {
    @Setter
    private String message;
    
    @Override
    public void sayHello() {
        System.out.println(message);
    }
}
