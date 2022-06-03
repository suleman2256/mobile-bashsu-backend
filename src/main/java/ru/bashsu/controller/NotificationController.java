package ru.bashsu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bashsu.dto.RestResponse;
import ru.bashsu.jpa.entity.Employee;
import ru.bashsu.jpa.entity.Notification;
import ru.bashsu.service.NotificationService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
@Api(description = "API для работы с уведомлениями")
public class NotificationController {

    private final NotificationService notificationService;

    @ApiOperation("Получение уведомления по ID")
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Notification>> getNotificationById(
            @ApiParam("ID") @PathVariable Long id) {

        try {
            Notification result = notificationService.getById(id);
            return RestResponse.success(result);
        } catch (Exception e) {
            val msg = String.format("Ошибка получения уведомления по ID [%s]", id);
            log.error(msg, e);
            return RestResponse.error(HttpStatus.BAD_REQUEST, msg);
        }
    }

    @ApiOperation("Создание/изменение уведомления")
    @PostMapping
    public ResponseEntity<RestResponse<Notification>> save(
            @ApiParam(value = "Запрос", required = true)
            @RequestBody Notification request) {

        try {
            Notification result = notificationService.save(request);
            return RestResponse.success(result);
        } catch (Exception e) {
            val msg = "Ошибка создания/изменения уведомления: " + e.getMessage();
            log.error(msg, e);
            return RestResponse.error(HttpStatus.BAD_REQUEST, msg);
        }
    }

    @ApiOperation("Удаление уведомления по ID")
    @DeleteMapping("{id}")
    public ResponseEntity<RestResponse<Boolean>> deleteById(
            @ApiParam("ID") @PathVariable Long id) {
        try {
            boolean result = notificationService.deleteById(id);
            return RestResponse.success(result);
        } catch (Exception e) {
            val msg = String.format("Ошибка удаления уведомления по ID [%s]", id);
            log.error(msg, e);
            return RestResponse.error(HttpStatus.BAD_REQUEST, msg);
        }

    }
}
