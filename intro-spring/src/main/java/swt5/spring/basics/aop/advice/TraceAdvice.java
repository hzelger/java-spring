package swt5.spring.basics.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class TraceAdvice {
    @Pointcut("execution(public * swt5.spring.basics.aop.logic..*find*(..))")
    private void findMethods(){
        
    }
    
    @Before("findMethods()")
    public void traceBefore(JoinPoint jp) {
        String methodName = jp.getTarget().getClass().getName() + "#" + jp.getSignature().getName();

        IO.println("--> %s".formatted(methodName));
    }

    @After("findMethods()")
    public void traceAfter(JoinPoint jp) {
        String methodName = jp.getTarget().getClass().getName() + "#" + jp.getSignature().getName();

        IO.println("<-- %s".formatted(methodName));
    }

    @Around("findMethods()")
    public Object traceAround(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getTarget().getClass().getName() + "#" + pjp.getSignature().getName();

        IO.println("==> %s".formatted(methodName));
        Object result = pjp.proceed();
        IO.println("<== %s".formatted(methodName));

        return result;
    }

    @AfterThrowing(value = "within(swt5.spring.basics.aop.logic..*) && " +
            "execution(public * *find*ById*(..))", throwing = "ex")
    public void traceException(JoinPoint jp, Throwable ex) {
        String methodName = jp.getTarget().getClass().getName() + "#" + jp.getSignature().getName();

        IO.println("!!> %s: %s".formatted(methodName, ex));
    }
}
