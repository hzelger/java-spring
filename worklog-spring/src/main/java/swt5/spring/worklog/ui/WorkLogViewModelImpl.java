package swt5.spring.worklog.ui;

import swt5.spring.worklog.domain.Employee;
import swt5.spring.worklog.logic.WorkLogService;

// WorkLogViewModelImpl surrounds all of its methods with a JpaInterceptor that
// that keeps the EntityManager open. This is important when lazily loaded child
// objects are accessed. Not doing so results in a LazyLoadingException.
public class WorkLogViewModelImpl implements WorkLogViewModel {
  
  private WorkLogService workLogService;

  
  @Override
  public void saveEmployees(Employee... employees) {
    for (Employee e : employees)
      workLogService.syncEmployee(e);
  }

  @Override
  public void findAll() {
    for (Employee e : workLogService.findAllEmployees()) {
      System.out.println(e);
      e.getLogbookEntries().forEach(entry -> System.out.println("   " + entry));
    }
  }
}
