package by.tms.davydovskaya.todo.service;

import by.tms.davydovskaya.todo.dtos.PassportDto;
import by.tms.davydovskaya.todo.entities.Passport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PassportService {

    PassportDto create(PassportDto passportDto);

    Page<Passport> getAll(Pageable pageable);

    List<PassportDto> findAll();
    PassportDto findById(Long id);
}
