package by.tms.davydovskaya.todo.repository;

import by.tms.davydovskaya.todo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User> {

//    @Query(nativeQuery = true, name = "SELECT * FROM system_user WHERE first_name = ? AND last_name = ?")
    Optional<User> findDistinctByFirstNameAndLastName(String firstName, String lastName);
    @Query("SELECT user FROM User user WHERE user.lastName = :lastName")
    Optional<User> findByLastName(@Param("lastName") String lastName);

    User findByLogin(String login);
}
