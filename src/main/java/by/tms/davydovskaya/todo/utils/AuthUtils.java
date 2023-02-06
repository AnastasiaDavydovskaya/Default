package by.tms.davydovskaya.todo.utils;

import by.tms.davydovskaya.todo.configs.security.CustomUserDetails;
import by.tms.davydovskaya.todo.entities.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class AuthUtils {

    public User getCurrentUser() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUser();
    }
}
