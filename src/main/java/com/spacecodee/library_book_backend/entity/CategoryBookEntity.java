package com.spacecodee.library_book_backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "category_book", schema = "public", catalog = "spacecodee")
public class CategoryBookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_book_id", nullable = false)
    private int categoryBookId;
    @Basic
    @Column(name = "category_book_name", nullable = false, length = 100)
    private String categoryBookName;
    @OneToMany(mappedBy = "categoryBookEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<BookEntity> booksEntity = new HashSet<>();

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryBookEntity that = (CategoryBookEntity) o;
        return categoryBookId == that.categoryBookId && Objects.equals(categoryBookName, that.categoryBookName);
    }

    @Override public int hashCode() {
        return Objects.hash(categoryBookId, categoryBookName);
    }
}
