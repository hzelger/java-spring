package swt5.spring.worklog.dao;

import jakarta.persistence.EntityManagerFactory;
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
import swt5.util.JpaUtil;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JpaConfig1.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Rollback(false)
public class JpaDaoTest {

  @Autowired
  private EmployeeDao employeeDao;
  @Autowired
  private EntityManagerFactory emFactory;

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

    employeeDao.insert(empl1);
    employeeDao.insert(empl2);
    employeeDao.insert(empl3);


    empl1Id = empl1.getId();
    empl2Id = empl2.getId();
    empl3Id = empl3.getId();

    initialized = true;
  }

  @Test
  public void testInsert1() {
    JpaUtil.executeInTransaction(emFactory, this::fillDatabase);
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

