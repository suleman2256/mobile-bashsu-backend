package ru.bashsu.service;

import ru.bashsu.jpa.entity.Employee;

public interface EmployeeService {
    Employee getById(Long id);
    Employee save(Employee employee);
    boolean deleteById(Long id);

    Employee getByLogin(String login);
}
