package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.book.BookEntity;
import com.spacecodee.library_book_backend.entity.book.projections.BookAndCategoryAndRatingsEntityInfo;
import com.spacecodee.library_book_backend.entity.book.projections.BookIdNameAuthorImageRatingsEntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookAndCategoryAndRatingsEntityInfo> findByBookId(int bookId);

    boolean existsByBookName(String name);

    List<BookIdNameAuthorImageRatingsEntityInfo> findByBookNameContainingIgnoreCase(String bookName);
}