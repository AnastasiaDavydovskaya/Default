package by.tms.davydovskaya.todo.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    T create(T value);
    T update(T value);
    List<T> findAll();
    T findById(Long id);
    void delete(Long id);
}
