package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRatingBookRepository extends JpaRepository<UserRatingBookEntity, UserRatingBookKeyEntity> {

    @Query("SELECT AVG(r.ratingBook) FROM UserRatingBookEntity r WHERE r.userRatingBookId.ratingBookId = ?1")
    Optional<Float> getPromedioRatingBook(int bookId);

    @Query("select u.ratingBook from UserRatingBookEntity u where u.userRatingBookId.ratingBookId = ?1 OR " +
            "u.userRatingBookId.ratingUserId = ?2")
    Optional<Float> findRatingBook(int bookId, int clientId);
}