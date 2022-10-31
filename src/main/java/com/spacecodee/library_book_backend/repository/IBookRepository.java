package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Integer> {

    Optional<BookEntity> findByBookName(String name);

    boolean existsByBookName(String name);
}