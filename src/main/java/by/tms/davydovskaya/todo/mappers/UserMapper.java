package by.tms.davydovskaya.todo.mappers;

import by.tms.davydovskaya.todo.dtos.auth.RegistrationRequest;
import by.tms.davydovskaya.todo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "login")
    User toEntity(RegistrationRequest registrationRequest);
}
