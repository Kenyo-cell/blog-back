package ru.blogback.config.userdetails;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlogUser {
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public BlogUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        authorities.add(new SimpleGrantedAuthority(role));
    }
}
