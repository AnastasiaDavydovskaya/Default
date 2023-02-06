package by.tms.davydovskaya.todo.repository;

import by.tms.davydovskaya.todo.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
}
