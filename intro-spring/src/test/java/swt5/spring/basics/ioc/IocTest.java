package swt5.spring.basics.ioc;

import org.junit.jupiter.api.Test;
import swt5.spring.basics.ioc.logic.factorybased.WorkLogServiceImpl;

public class IocTest {

  @Test
  public void testFactoryApproach() {
    WorkLogServiceImpl workLog = new WorkLogServiceImpl();
    workLog.findAllEmployees();
    workLog.findEmployeeById(1L);
  }

  @Test
  public void testXmlConfig() {
  }

  @Test
  public void testJavaConfig() {
  }
}
