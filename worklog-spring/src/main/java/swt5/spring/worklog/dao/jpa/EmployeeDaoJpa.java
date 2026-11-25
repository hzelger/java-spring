package swt5.spring.worklog.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import swt5.spring.worklog.dao.EmployeeDao;
import swt5.spring.worklog.domain.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeDaoJpa implements EmployeeDao {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Employee> findAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public void insert(Employee entity) {
        em.persist(entity);
    }

    @Override
    public Employee merge(Employee entity) {
        return em.merge(entity);
    }
}
