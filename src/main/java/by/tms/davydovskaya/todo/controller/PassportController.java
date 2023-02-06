package by.tms.davydovskaya.todo.controller;

import by.tms.davydovskaya.todo.dtos.PassportDto;
import by.tms.davydovskaya.todo.entities.Passport;
import by.tms.davydovskaya.todo.service.PassportService;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/passports")
@AllArgsConstructor
public class PassportController {

    private final PassportService passportService;

    @PostMapping
    public PassportDto create(@RequestBody @Valid PassportDto passportDto) {
        return passportService.create(passportDto);
    }

    @GetMapping
    public Page<Passport> getAll(@ParameterObject Pageable pageable) {
        return passportService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public PassportDto getById(@PathVariable Long id) {
        return passportService.findById(id);
    }
}
