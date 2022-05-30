package ru.bashsu.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bashsu.jpa.entity.Employee;
import ru.bashsu.service.EmployeeService;

import java.util.Objects;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Api(description = "API для работы c авторизацией")
public class AuthController {

    private EmployeeService employeeService;

    @PostMapping(path = "/login")
    public @ResponseBody Employee getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        User user = (principal instanceof User) ? (User) principal : null;
        return Objects.nonNull(user) ? employeeService.getByLogin(user.getUsername()) : null;
    }
}
