package swt5.spring.basics.aop.logic;

import swt5.spring.basics.aop.annotation.Traceable;
import swt5.spring.basics.aop.logic.Employee;
import swt5.spring.basics.aop.logic.EmployeeIdNotFoundException;
import swt5.spring.basics.aop.logic.WorkLogService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkLogServiceImpl implements WorkLogService {

	private Map<Long, Employee> employees = new HashMap<>();

	private void init() {
		employees.put(1L, new Employee(1L, "Bill", "Gates"));
		employees.put(2L, new Employee(2L, "James", "Goslin"));
		employees.put(3L, new Employee(3L, "Bjarne", "Stroustrup"));
	}

	public WorkLogServiceImpl() {
		init();
	}

	@Traceable
	public Employee findEmployeeById(Long id) throws EmployeeIdNotFoundException {
		if (employees.get(id) == null)
			throw new EmployeeIdNotFoundException();
		return employees.get(id);
	}

	@Traceable
	public List<Employee> findAllEmployees() {
		return new ArrayList<Employee>(employees.values());
	}
}