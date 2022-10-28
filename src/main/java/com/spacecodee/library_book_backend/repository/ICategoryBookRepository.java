package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.CategoryBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryBookRepository extends JpaRepository<CategoryBookEntity, Integer> {
    Optional<CategoryBookEntity> findByCategoryBookName(String name);

    boolean existsByCategoryBookName(String name);
}