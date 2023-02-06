package by.tms.davydovskaya.todo.service;

import by.tms.davydovskaya.todo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);
    User update(User user);
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);

    void deleteById();
    void deleteById(Long id);
    User findByFirstNameAndLastName(String firstName, String lastName);
    Page<User> getByPage(Pageable pageable);
}
