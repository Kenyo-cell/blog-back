package ru.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blogback.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    void deleteByUsername(String username);
    Optional<User> findByUsername(String name);
}
