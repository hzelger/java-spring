package swt5.spring.worklog.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import swt5.spring.worklog.config.JdbcConfig;
import swt5.spring.worklog.domain.Employee;
import swt5.util.DbScriptRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;

import static swt5.util.PrintUtil.printTitle;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JdbcConfig.class)
public class JdbcDaoTest {

    //
    // inject DataSource and EmployeeDao
    //
    @Autowired
    private DataSource dataSource;
    @Autowired
    private EmployeeDao employeeDao;

    private void createSchema(DataSource ds, String ddlScript) {
        try {
            DbScriptRunner scriptRunner = new DbScriptRunner(ds.getConnection());
            InputStream is =
                    JdbcDaoTest.class.getClassLoader().getResourceAsStream(ddlScript);
            if (is == null) throw new IllegalArgumentException(
                    String.format("File %s not found in classpath.", ddlScript));
            scriptRunner.runScript(new InputStreamReader(is));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJdbc() {

        printTitle("create schema", 60, '-');
        createSchema(dataSource, "worklog-db-schema.sql");

        printTitle("insert employee", 60, '-');
        Employee empl1 =
                new Employee("Josefine", "Feichtlbauer", LocalDate.of(1970, 10, 26));
        employeeDao.insert(empl1);
        System.out.println("empl1 = " + (empl1 == null ? (null) : empl1.toString()));

        printTitle("update employee", 60, '-');
        empl1.setFirstName("Jaquira");
        empl1 = employeeDao.merge(empl1);
        System.out.println("empl1 = " + (empl1 == null ? (null) : empl1.toString()));

        printTitle("read all", 50, '-');
        
        employeeDao.findAll().forEach(IO::println);
    }
}
