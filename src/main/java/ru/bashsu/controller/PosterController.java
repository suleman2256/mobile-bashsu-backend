package ru.bashsu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.bashsu.dto.PosterDto;
import ru.bashsu.jpa.entity.Poster;
import ru.bashsu.service.PosterService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/poster")
@Api(description = "API для работы с мероприятиями")
public class PosterController {

    private final PosterService posterService;

    @ApiOperation("Получение мероприятия по ID")
    @GetMapping("/{id}")
    public Poster getPosterById(
            @ApiParam("ID") @PathVariable Long id) {

        try {
            return posterService.getById(id);
        } catch (Exception e) {
            val msg = String.format("Ошибка получения мероприятия по ID [%s]", id);
            log.error(msg, e);
            throw new IllegalStateException(msg);
        }
    }

    @ApiOperation("Создание/изменение мероприятия")
    @PostMapping
    public Poster save(
            @ApiParam(value = "Запрос", required = true)
            @RequestBody Poster request) {

        try {
            return posterService.save(request);
        } catch (Exception e) {
            val msg = "Ошибка создания/изменения мероприятия: " + e.getMessage();
            log.error(msg, e);
            throw new IllegalStateException(msg);
        }
    }

    @ApiOperation("Удаление мероприятия по ID")
    @DeleteMapping("{id}")
    public Boolean deleteById(
            @ApiParam("ID") @PathVariable Long id) {
        try {
            return posterService.deleteById(id);
        } catch (Exception e) {
            val msg = String.format("Ошибка удаления мероприятия по ID [%s]", id);
            log.error(msg, e);
            throw new IllegalStateException(msg);
        }
    }

    @ApiOperation("Получение всех мероприятий")
    @GetMapping("/all")
    public List<PosterDto> getAllPoster() {
        try {
            return posterService.findAll();
        } catch (Exception e) {
            val msg = "Ошибка получения всех мероприятий";
            log.error(msg, e);
            throw new IllegalStateException(msg);
        }
    }
}
