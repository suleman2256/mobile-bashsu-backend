package ru.bashsu.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.bashsu.jpa.entity.Notification;
import ru.bashsu.jpa.entity.Poster;

public interface PosterRepository extends JpaRepository<Poster, Long>,
        JpaSpecificationExecutor<Poster> {
}
