package com.spacecodee.library_book_backend.entity.book.projections;

import com.spacecodee.library_book_backend.entity.rating.projections.RatingBookEntityInfo;

import java.util.Set;

/**
 * A Projection for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
public interface BookIdNameAuthorImageRatingsEntityInfo extends BookIdNameAuthorImageEntityInfo {
    Set<RatingBookEntityInfo> getRatingBooksEntity();
}