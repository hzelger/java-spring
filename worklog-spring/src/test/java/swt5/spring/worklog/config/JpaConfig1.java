package swt5.spring.worklog.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import swt5.spring.worklog.dao.EmployeeDao;
import swt5.spring.worklog.dao.EmployeeRepository;
import swt5.spring.worklog.dao.GenericDao;
import swt5.spring.worklog.dao.jpa.EmployeeDaoJpa;
import swt5.spring.worklog.logic.WorkLogService;
import swt5.spring.worklog.logic.WorkLogServiceImpl;

@Configuration
@EnableTransactionManagement
@Import(JpaDataSourceConfig.class)
@EnableJpaRepositories(basePackageClasses = GenericDao.class)
public class JpaConfig1 {

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }
  
  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }

  // ============================== DATA ACCESS LAYER =============================
  @Bean
  public EmployeeDao employeeDaoJpa() {
    return new EmployeeDaoJpa();
  }
  
  //============================= BUSINESS LOGIC LAYER ============================
  
  @Bean
  public WorkLogService workLogService(EmployeeDao employeeDao) {
    return new WorkLogServiceImpl(employeeDao);
  }

  // ============================== PRESENTATION LAYER  ===========================
}