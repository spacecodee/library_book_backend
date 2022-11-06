package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRatingBookRepository extends JpaRepository<UserRatingBookEntity, UserRatingBookKeyEntity> {

    Optional<UserRatingBookEntity> getByUserRatingBookId(UserRatingBookKeyEntity id);

    boolean existsByUserRatingBookId(UserRatingBookKeyEntity id);
}