package by.tms.davydovskaya.todo.dtos.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    String token;
}
