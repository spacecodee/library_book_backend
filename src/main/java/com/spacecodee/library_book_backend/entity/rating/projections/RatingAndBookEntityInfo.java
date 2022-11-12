package com.spacecodee.library_book_backend.entity.rating.projections;

import com.spacecodee.library_book_backend.entity.book.projections.BookAndCategoryNameEntityInfo;

/**
 * A Projection for the {@link com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity} entity
 */
public interface RatingAndBookEntityInfo extends RatingBookEntityInfo {
    BookAndCategoryNameEntityInfo getBookEntity();
}