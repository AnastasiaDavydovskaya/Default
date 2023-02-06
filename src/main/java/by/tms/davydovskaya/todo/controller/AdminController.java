package by.tms.davydovskaya.todo.controller;

import by.tms.davydovskaya.todo.entities.User;
import by.tms.davydovskaya.todo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admins")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
