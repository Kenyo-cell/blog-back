package ru.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blogback.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
