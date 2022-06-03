package ru.bashsu.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bashsu.jpa.entity.Employee;
import ru.bashsu.jpa.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Не найден студент с ID " + id));
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean deleteById(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Не удалось удалить студента с ID " + id, e);
            return false;
        }
    }

    public Employee getByLogin(String login) {
        return employeeRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee e = getByLogin(login);
        if (Objects.isNull(e)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }

        return new User(e.getLogin(), e.getPassword(), true, true, true, true, new HashSet<>());
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll().stream().sorted(Comparator.comparing(Employee::getId)).collect(Collectors.toList());
    }
}
