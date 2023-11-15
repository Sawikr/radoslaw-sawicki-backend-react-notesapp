package com.radoslawsawicki.backendreactnotesapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "CATEGORIES")
public class Category {

    private Long id;
    private String categoryName;
    private String username;

    public Category(Long id, String categoryName, String username) {
        this.id = id;
        this.categoryName = categoryName;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name = "CATEGORY_ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "CATEGORY_NAME")
    public String getCategoryName() {
        return categoryName;
    }

    @NonNull
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(categoryName, category.categoryName) && Objects.equals(username, category.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName, username);
    }
}
