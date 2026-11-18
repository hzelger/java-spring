package swt5.spring.worklog.dao.jdbc;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import swt5.spring.worklog.dao.EmployeeDao;
import swt5.spring.worklog.domain.Employee;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoJdbc implements EmployeeDao {
    private static class EmployeeMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate());
        }
    }

    @Setter
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findAll() {
        final String sql = 
                "select id, firstname, lastname, DATEOFBIRTH from Employee";
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    @Override
    public Optional<Employee> findById(Long id) {
        final String sql =
                "select id, firstname, lastname, dateofbirth from Employee " +
                        "where id = ?";
        var employees = jdbcTemplate.query(sql,new Object[] {id}, new EmployeeMapper());
        if (employees.size() > 1)
            throw new IncorrectResultSizeDataAccessException(1, employees.size());
        
        return employees.stream().findFirst();
    }

    // Version 1: Data access code without Spring
    public void insert1(final Employee e) throws DataAccessException {
        final String sql =
                "insert into EMPLOYEE (FIRSTNAME, LASTNAME, DATEOFBIRTH) "
                        + "values (?, ?, ?)";
        try (Connection conn = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getFirstName());
            stmt.setString(2, e.getLastName());
            stmt.setDate(3, Date.valueOf(e.getDateOfBirth()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insert2(final Employee e) throws DataAccessException {
        final String sql =
                "insert into EMPLOYEE (FIRSTNAME, LASTNAME, DATEOFBIRTH) "
                        + "values (?, ?, ?)";

        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, e.getFirstName());
                ps.setString(2, e.getLastName());
                ps.setDate(3, Date.valueOf(e.getDateOfBirth()));
            }
        });
    }

    public void insert3(final Employee e) throws DataAccessException {
        final String sql =
                "insert into EMPLOYEE (FIRSTNAME, LASTNAME, DATEOFBIRTH) "
                        + "values (?, ?, ?)";

        jdbcTemplate.update(sql, e.getFirstName(), e.getLastName(), e.getDateOfBirth());
    }

    public void insert(final Employee e) throws DataAccessException {
        final String sql =
                "insert into EMPLOYEE (FIRSTNAME, LASTNAME, DATEOFBIRTH) "
                        + "values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                                    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                                    ps.setString(1, e.getFirstName());
                                    ps.setString(2, e.getLastName());
                                    ps.setDate(3, Date.valueOf(e.getDateOfBirth()));
                                    return ps;
                                }
                            },
                keyHolder
        );

        e.setId(keyHolder.getKeyAs(Long.class));
    }

    @Override
    public Employee merge(Employee entity) {
        return null;
    }
}
