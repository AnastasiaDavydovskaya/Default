package by.tms.davydovskaya.todo.service.impl;

import by.tms.davydovskaya.todo.entities.Role;
import by.tms.davydovskaya.todo.entities.User;
import by.tms.davydovskaya.todo.exceptions.ResourceNotFoundException;
import by.tms.davydovskaya.todo.repository.RoleRepository;
import by.tms.davydovskaya.todo.repository.UserRepository;
import by.tms.davydovskaya.todo.service.UserService;
import by.tms.davydovskaya.todo.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

//    @Override
//    @Transactional
//    public void assignTaskToUser(Long userId, Task task) {
//        final User foundUser = null;
//
//        Optional<User> user = userRepository.findById(userId);
//        if(user.isPresent()) {
//            foundUser = user.get();
//            task.setUser(foundUser);
//            Task savedTask = taskRepository.create(task);
//            foundUser.getTasks().add(savedTask);
//        }
//
//        transactionTemplate.execute(transaction -> {
//            try {
//                update(foundUser);
//            } catch (Exception e) {
//                taskRepository.delete(task);
//                transaction.setRollbackOnly();
//            }
//            return null;
//        });
//    }

    @Override
    @Transactional
    public User create(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if(user != null) {
            if(passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteById() {
        User currentUser = AuthUtils.getCurrentUser();
        userRepository.deleteById(currentUser.getId());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findDistinctByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cannot find user by first name=%s and last name=%s", firstName, lastName)
                ));
    }

    @Override
    public Page<User> getByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
