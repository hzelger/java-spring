package swt5.spring.worklog.ui;

import static swt5.util.PrintUtil.printTitle;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swt5.spring.worklog.domain.Employee;
import swt5.spring.worklog.domain.LogbookEntry;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {})
public class UITest {

  //
  // TODO: Inject WorkLogViewModel
  //

  @Test
  public void uiTest() {
    Employee empl1 = new Employee("Sepp", "Forcher", LocalDate.of(1935, 12, 12));
    Employee empl2 = new Employee("Alfred", "Kunz", LocalDate.of(1944, 8, 10));
    Employee empl3 = new Employee("Sigfried", "Hinz", LocalDate.of(1954, 5, 3));

    LogbookEntry entry1 = new LogbookEntry("Analyse",
      LocalDateTime.of(2018, 3, 1, 10, 0), LocalDateTime.of(2018, 3, 1, 13, 45));
    LogbookEntry entry2 = new LogbookEntry("Implementierung",
      LocalDateTime.of(2018, 3, 1, 10, 15), LocalDateTime.of(2018, 3, 1, 14, 30));
    LogbookEntry entry3 = new LogbookEntry("Testen",
      LocalDateTime.of(2018, 3, 1, 10, 15), LocalDateTime.of(2018, 3, 1, 14, 30));

    printTitle("saveEmployees", 60, '-');
    //
    // TODO: Insert employees
    //


    empl1.addLogbookEntry(entry1);
    empl1.addLogbookEntry(entry2);
    empl2.addLogbookEntry(entry3);

    printTitle("saveEmployees", 60, '-');
    //
    // TODO: Update employees
    //

    printTitle("findAll", 60, '-');
    //
    // TODO: Print all employees with logbook entries
    //
  }
}