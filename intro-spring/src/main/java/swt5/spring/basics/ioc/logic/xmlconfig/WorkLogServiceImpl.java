package swt5.spring.basics.ioc.logic.xmlconfig;

import java.io.IOException;
import java.util.*;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swt5.spring.basics.ioc.domain.Employee;
import swt5.spring.basics.ioc.util.Logger;
import swt5.spring.basics.ioc.util.LoggerFactory;

@Service
public class WorkLogServiceImpl implements WorkLogService {
  private Map<Long, Employee> employees = new HashMap<>();

  @Setter
  @Autowired
  private Logger logger;
  
  private void initLogger() {
    Properties props = new Properties();
   
    try {
      ClassLoader cl = this.getClass().getClassLoader();
      props.load(cl.getResourceAsStream(
        "swt5/spring/basics/ioc/worklog.properties"));
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    String type = props.getOrDefault("logger.type", "console").toString();
    this.logger = LoggerFactory.getLogger(type);
    
  }

  private void init() {
    employees.put(1L, new Employee(1L, "Bill", "Gates"));
    employees.put(2L, new Employee(2L, "James", "Goslin"));
    employees.put(3L, new Employee(3L, "Bjarne", "Stroustrup"));
  }

  public WorkLogServiceImpl() {
    initLogger();
    init();
  }

  public WorkLogServiceImpl(Logger logger) {
    this.logger = logger;
    init();
  }


  @Override
  public Employee findEmployeeById(Long id) {
    var empl = employees.get(id);
    logger.log("findEmployeeById(" + id + "): " + empl);
    return  empl;
  }

  @Override
  public List<Employee> findAllEmployees() {
    logger.log("findAllEmployees()");
    return new ArrayList<Employee>(employees.values());
  }
}
