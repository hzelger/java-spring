package swt5.spring.worklog.logic;

import static swt5.util.PrintUtil.printTitle;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swt5.spring.worklog.domain.Employee;
import swt5.spring.worklog.domain.LogbookEntry;

@SuppressWarnings("Duplicates")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {})
public class LogicWithJpaTest {

  //
  // TODO: inject business logic component
  //

  private void createEmployeesWithLogbookEntries(WorkLogService workLog) {
    var empl1 = new Employee("Sepp", "Forcher", LocalDate.of(1935, 12, 12));
    var empl2 = new Employee("Alfred", "Kunz", LocalDate.of(1944, 8, 10));
    var empl3 = new Employee("Sigfried", "Hinz", LocalDate.of(1954, 5, 3));

    LogbookEntry entry1 = new LogbookEntry("Analyse",
      LocalDateTime.of(2018, 3, 1, 10, 0), LocalDateTime.of(2018, 3, 1, 11, 30));
    LogbookEntry entry2 = new LogbookEntry("Implementierung",
      LocalDateTime.of(2018, 3, 1, 11, 30), LocalDateTime.of(2018, 3, 1, 16, 30));
    LogbookEntry entry3 = new LogbookEntry("Testen",
      LocalDateTime.of(2018, 3, 1, 10, 15), LocalDateTime.of(2018, 3, 1, 14, 30));

    empl1.addLogbookEntry(entry1);
    empl1.addLogbookEntry(entry2);
    empl2.addLogbookEntry(entry3);

    empl1 = workLog.syncEmployee(empl1);
    empl2 = workLog.syncEmployee(empl2);
    empl3 = workLog.syncEmployee(empl3);
  }

  private void printEmployeesWithLogbookEntries(WorkLogService workLog) {
  }

  @Test
  public void testBusinessLogicWithJpaDaos() {
    printTitle("createEmployeesWithLogbookEntries", 60, '-');

    printTitle("printEmployeesWithLogbookEntries", 60, '-');
  }
}