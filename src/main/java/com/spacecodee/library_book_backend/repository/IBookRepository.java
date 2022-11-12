package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.book.BookEntity;
import com.spacecodee.library_book_backend.entity.book.projections.BookAndCategoryAndRatingsEntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookAndCategoryAndRatingsEntityInfo> findByBookIdAndRatingBooksEntityUserRatingBookIdRatingUserId(
            int bookId, int ratingUserId);

    boolean existsByBookName(String name);
}