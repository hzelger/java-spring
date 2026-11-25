package swt5.spring.worklog.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swt5.spring.worklog.domain.Employee;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Rollback(false)
public class JpaDaoTest {

  //
  // TODO: Inject EntityManagerFactory and DAO
  //

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

    //
    // TODO: Insert employees
    //

    empl1Id = empl1.getId();
    empl2Id = empl2.getId();
    empl3Id = empl3.getId();

    initialized = true;
  }

  @Test
  public void testInsert() {
  }

  @Test
  public void testUpdate() {
  }

  @Test
  public void testFindById() {
  }

  @Test
  public void testFindAll() {
  }
}

