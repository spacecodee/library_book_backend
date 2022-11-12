package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity;
import com.spacecodee.library_book_backend.entity.rating.projections.RatingAndBookEntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRatingBookRepository extends JpaRepository<UserRatingBookEntity, UserRatingBookKeyEntity> {
    Optional<RatingAndBookEntityInfo> getByUserClientEntityUserIdAndBookEntityBookId(int userId, int bookId);

    @Query("SELECT AVG(r.ratingBook) FROM UserRatingBookEntity r WHERE r.userRatingBookId.ratingBookId = ?1")
    Optional<Float> getPromedioRatingBook(int bookId);

    @Query("select u.ratingBook from UserRatingBookEntity u where u.bookEntity.bookId = ?1 AND u.userClientEntity.userId = ?2")
    Float findByRatingBook(int bookId, int clientId);

    boolean existsByUserRatingBookId(UserRatingBookKeyEntity id);
}