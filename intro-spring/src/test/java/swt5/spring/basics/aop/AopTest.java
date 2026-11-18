package swt5.spring.basics.aop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swt5.spring.basics.aop.logic.WorkLogService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AopConfig.class})
public class AopTest {
    @Autowired
    private WorkLogService worklog;

    @Test
    public void testAop() {
        try (AbstractApplicationContext appCxt =
                     new AnnotationConfigApplicationContext(AopConfig.class)) {
            worklog = appCxt.getBean(WorkLogService.class);

            worklog.findAllEmployees();

            try {
                for (long i = 0; i < 3; i++) {
                    worklog.findEmployeeById(i);
                }
            } catch (Exception ignore) {
                // ignore
            }

        }
    }
}
