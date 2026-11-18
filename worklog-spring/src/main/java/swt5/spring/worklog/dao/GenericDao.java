package swt5.spring.worklog.dao;

import swt5.spring.worklog.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    void insert(T entity);

    T merge(T entity);
}
