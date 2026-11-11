package swt5.spring.basics.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swt5.spring.basics.ioc.logic.Log;
import swt5.spring.basics.ioc.logic.javaconfig.WorkLogServiceImpl;
import swt5.spring.basics.ioc.util.Logger;

@Configuration
public class IocConfig {
    @Bean // name can be specified via @Bean("consoleLogger") or @Bean(name="consoleLogger")
    public Logger consoleLogger() {
        return new swt5.spring.basics.ioc.util.ConsoleLogger();
    }
    
    @Bean
    public Logger fileLogger() {
        return new swt5.spring.basics.ioc.util.FileLogger();
    }
    
    @Bean
    public WorkLogServiceImpl worklog(@Log(Log.Type.STANDARD) Logger logger) {
        return new WorkLogServiceImpl(logger);
    }
}
