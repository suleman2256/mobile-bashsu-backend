package ru.bashsu.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bashsu.jpa.entity.Notification;
import ru.bashsu.jpa.repository.NotificationRepository;

import javax.persistence.EntityNotFoundException;

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
}