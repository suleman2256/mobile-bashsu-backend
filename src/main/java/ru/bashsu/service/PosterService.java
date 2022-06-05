package ru.bashsu.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bashsu.dto.PosterDto;
import ru.bashsu.jpa.entity.Employee;
import ru.bashsu.jpa.entity.Poster;
import ru.bashsu.jpa.repository.EmployeeRepository;
import ru.bashsu.jpa.repository.PosterRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PosterService {

    private final PosterRepository posterRepository;

    public Poster getById(Long id) {
        return posterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Не найдено мероприятие с ID " + id));
    }

    public Poster save(Poster poster) {
        return posterRepository.save(poster);
    }

    public boolean deleteById(Long id) {
        try {
            posterRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Не удалось удалить мероприятие с ID " + id, e);
            return false;
        }
    }

    public List<PosterDto> findAll() {
        List<Poster> posterList = posterRepository.findAll();
        List<PosterDto> result = new ArrayList<>();
        for (Poster p : posterList) {
            PosterDto posterDto = new PosterDto(p);
            result.add(posterDto);
        }
        return result.stream().sorted(Comparator.comparing(PosterDto::getId).reversed()).collect(Collectors.toList());
    }
}
