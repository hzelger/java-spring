package swt5.spring.basics.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import swt5.spring.basics.ioc.logic.xmlconfig.WorkLogServiceImpl;

public class IocTest {

  @Test
  public void testFactoryApproach() {
    WorkLogServiceImpl workLog = new WorkLogServiceImpl();
    workLog.findAllEmployees();
    workLog.findEmployeeById(1L);
  }

  @Test
  public void testXmlConfig() {
    try (AbstractApplicationContext appCtx = 
                 new ClassPathXmlApplicationContext("swt5/spring/basics/ioc/applicationContext-xml-config.xml")) {
      var workLog = appCtx.getBean("worklog", WorkLogServiceImpl.class);
      workLog.findAllEmployees();
      workLog.findEmployeeById(1L);
        
    }
  }

  @Test
  public void testJavaConfig() {
  }
}
