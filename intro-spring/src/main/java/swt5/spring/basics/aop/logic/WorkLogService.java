package swt5.spring.basics.aop.logic;

import swt5.spring.basics.aop.logic.Employee;
import swt5.spring.basics.aop.logic.EmployeeIdNotFoundException;

import java.util.List;

public interface WorkLogService {
  public Employee findEmployeeById(Long id) throws EmployeeIdNotFoundException;
  public List<Employee> findAllEmployees();
}
