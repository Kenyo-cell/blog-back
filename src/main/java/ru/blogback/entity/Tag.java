package ru.blogback.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tags")
public class Tag implements Serializable {
    @ToString.Include
    @Id
    @Column(name = "label", nullable = false)
    private String label;

    @ToString.Include
    @Column(name = "color")
    private String color;

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private List<Post> posts = new java.util.ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return label.equals(tag.label) && color.equals(tag.color) && posts.equals(tag.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, color, posts);
    }
}
