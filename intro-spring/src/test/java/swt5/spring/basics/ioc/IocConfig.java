package swt5.spring.basics.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import swt5.spring.basics.ioc.logic.Log;
import swt5.spring.basics.ioc.logic.javaconfig.WorkLogServiceImpl;
import swt5.spring.basics.ioc.util.Logger;

@Configuration
@ComponentScan(basePackageClasses = swt5.spring.basics.ioc.util.Logger.class)
public class IocConfig {
    // can be found via component scan, if @Component is defined on the classes
//    @Bean // name can be specified via @Bean("consoleLogger") or @Bean(name="consoleLogger")
//    public Logger consoleLogger() {
//        return new swt5.spring.basics.ioc.util.ConsoleLogger();
//    }
//    
//    @Bean
//    public Logger fileLogger() {
//        return new swt5.spring.basics.ioc.util.FileLogger();
//    }
    
    @Bean
    public WorkLogServiceImpl worklog(@Log(Log.Type.STANDARD) Logger logger) {
        return new WorkLogServiceImpl(logger);
    }
}
