package ru.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blogback.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {
}
