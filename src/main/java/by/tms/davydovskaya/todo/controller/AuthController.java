package by.tms.davydovskaya.todo.controller;

import by.tms.davydovskaya.todo.dtos.auth.AuthRequest;
import by.tms.davydovskaya.todo.dtos.auth.AuthResponse;
import by.tms.davydovskaya.todo.dtos.auth.RegistrationRequest;
import by.tms.davydovskaya.todo.entities.User;
import by.tms.davydovskaya.todo.mappers.UserMapper;
import by.tms.davydovskaya.todo.service.UserService;
import by.tms.davydovskaya.todo.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/rest/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());
        String token = JwtUtils.generateToken(user.getLogin());
        return AuthResponse.builder().token(token).build();
    }

    @PostMapping("/rest/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        User user = userMapper.toEntity(registrationRequest);
        userService.create(user);
    }
}
