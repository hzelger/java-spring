package swt5.spring.worklog.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import swt5.spring.worklog.config.JpaConfig1;
import swt5.spring.worklog.domain.Employee;

import java.time.LocalDate;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaConfig1.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Rollback(false)
public class SpringDataRepositoryTest {
  @Autowired
  private EmployeeRepository employeeRepository;

  private boolean  initialized = false;
  private Long empl1Id;
  private Long empl2Id;
  private Long empl3Id;

  private void fillDatabase() {
    if (initialized) return;

    Employee empl1 = new Employee("Johann", "Hinterseer", LocalDate.of(1954, 2, 2));
    Employee empl2 = new Employee("Suzi", "Quatro", LocalDate.of(1950, 6, 3));
    Employee empl3 = new Employee("Herbert", "Grönemeyer", LocalDate.of(1956, 4,
      2));
   
    employeeRepository.save(empl1);
    employeeRepository.save(empl2);
    employeeRepository.save(empl3);

    empl1Id = empl1.getId();
    empl2Id = empl2.getId();
    empl3Id = empl3.getId();

    initialized = true;
  }

  @Test
  @Transactional
  public void testInsert() {
    fillDatabase();
  }
  

  @Test
  @Transactional
  public void testFindById() {
    fillDatabase();

    var empl1 = employeeRepository.findById(empl1Id);
    IO.println("emp1 = %s".formatted(empl1.map(Employee::toString).orElse("<none>")));


    var empl2 = employeeRepository.findById(9999L);
    IO.println("emp2 = %s".formatted(empl2.map(Employee::toString).orElse("<none>")));
  }

  @Test
  @Transactional
  public void testFindAll() {
    fillDatabase();
    employeeRepository.findAll().forEach(IO::println);
  }

  @Test
  @Transactional
  public void testUpdate() {
    fillDatabase();
    var empl1 = employeeRepository.findById(empl1Id);
    if (empl1.isPresent()) {
      empl1.get().setFirstName("Hansi");
      empl1.get().setLastName("Hinterseer");
      // auto updated via first level caching
    }
    employeeRepository.findById(empl1Id).ifPresent(e -> IO.println("updated emp1 = %s".formatted(e)));
  }

  @Test
  @Transactional
  public void testQueries() {
    fillDatabase();
    
    var empl1 = employeeRepository.findByLastName("Hinterseer");
    IO.println("emp1 = %s".formatted(empl1.map(Employee::toString).orElse("<none>")));
    
    var empl2 = employeeRepository.findByLastNameContaining("seer");
    IO.println("emp2 = %s".formatted(empl2.stream().map(Employee::toString).collect(Collectors.joining(", "))));
  }
}

