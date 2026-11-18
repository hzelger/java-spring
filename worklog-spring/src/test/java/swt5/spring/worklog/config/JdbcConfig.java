package swt5.spring.worklog.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import swt5.spring.worklog.dao.EmployeeDao;
import swt5.spring.worklog.dao.jdbc.EmployeeDaoJdbc;

import javax.sql.DataSource;

@Configuration
@PropertySource("jdbc.properties")
public class JdbcConfig {

    @Value("${database.driverClassName}")
    private String driverClassName;

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    // ========================= DAO DEFINITIONS ================================
    @Bean
    public EmployeeDao employeeDaoJdbc(DataSource ds) {
      var ed = new EmployeeDaoJdbc();
      ed.setDataSource(ds);
      return ed;
    }
    // ====================== BUSINESS OBJECT DEFINIONS =========================
}