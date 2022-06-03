package ru.bashsu.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bashsu.jpa.entity.Employee;
import ru.bashsu.jpa.entity.Notification;
import ru.bashsu.jpa.repository.NotificationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification getById(Long id) {
        return notificationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Не найдено уведомление с ID " + id));
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public boolean deleteById(Long id) {
        try {
            notificationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Не удалось удалить уведомление с ID " + id, e);
            return false;
        }
    }

    public List<Notification> getByLogin(String login) {
        List<Notification> result = new ArrayList<>();
        List<Notification> all = notificationRepository.findAll();
        for (Notification notification : all) {
            for (Employee employee : notification.getEmployees().getEmployees()) {
                if (login.equals(employee.getLogin())) {
                    result.add(notification);
                }
            }
        }

        return result;
    }
}
