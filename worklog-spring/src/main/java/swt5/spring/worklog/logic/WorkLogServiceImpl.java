package swt5.spring.worklog.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import swt5.spring.worklog.dao.EmployeeDao;
import swt5.spring.worklog.domain.Employee;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class WorkLogServiceImpl implements WorkLogService {
    private final EmployeeDao employeeDao;

    @Override
    public Employee syncEmployee(Employee employee) {
        return employeeDao.merge(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAllEmployees() {
        return employeeDao.findAll();
    }
}
