package com.spacecodee.library_book_backend.entity.book.projections;

import com.spacecodee.library_book_backend.entity.category.book.projections.CategoryBookNameEntityInfo;
import com.spacecodee.library_book_backend.entity.book.BookEntity;

/**
 * A Projection for the {@link BookEntity} entity
 */
public interface BookAndCategoryAndRatingsEntityInfo extends BookIdNameAuthorImageRatingsEntityInfo {
    int getBookPages();

    String getBookUrlPdf();

    String getBookDescription();

    CategoryBookNameEntityInfo getCategoryBookEntity();
}