package ru.blogback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {
    @ToString.Include
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @ToString.Include
    @Column(name = "pass_hash")
    @JsonIgnore
    private String passwordHash;

    @ToString.Include
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ToString.Include
    @Column(name = "country")
    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Post> posts = new java.util.ArrayList<>();

    @ManyToMany(mappedBy = "likedUsers", cascade = CascadeType.ALL)
    private List<Post> likedPosts = new java.util.ArrayList<>();

    @JsonProperty("password")
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && passwordHash.equals(user.passwordHash) && createdAt.equals(user.createdAt) && country.equals(user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordHash, createdAt, country);
    }
}
