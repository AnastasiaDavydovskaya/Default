package by.tms.davydovskaya.todo.repository;

import by.tms.davydovskaya.todo.entities.Role;

public interface RoleRepository extends AbstractRepository<Role> {

    Role findByName(String name);
}
