package swt5.spring.worklog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(JpaDataSourceConfig.class)
public class JpaConfig1 {

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  // ============================== DATA ACCESS LAYER =============================

  //============================= BUSINESS LOGIC LAYER ============================

  // ============================== PRESENTATION LAYER  ===========================
}