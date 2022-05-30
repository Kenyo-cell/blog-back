package ru.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blogback.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
