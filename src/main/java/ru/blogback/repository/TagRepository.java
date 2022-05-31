package ru.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blogback.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, String> {
    boolean existsByLabel(String label);
    void deleteByLabel(String label);
    Optional<Tag> findByLabel(String label);
}
