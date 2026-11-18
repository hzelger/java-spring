package swt5.spring.basics.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import swt5.spring.basics.aop.advice.TraceAdvice;
import swt5.spring.basics.aop.logic.WorkLogService;
import swt5.spring.basics.aop.logic.WorkLogServiceImpl;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    public WorkLogService worklog() {
        return new WorkLogServiceImpl();
    }
    
    @Bean
    public TraceAdvice traceAdvice() {
        return new TraceAdvice();
    }
}
