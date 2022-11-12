package com.spacecodee.library_book_backend.entity.book.projections;

import com.spacecodee.library_book_backend.entity.category.book.projections.CategoryBookNameEntityInfo;

/**
 * A Projection for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
public interface BookAndCategoryNameEntityInfo extends BookIdNameAuthorImageEntityInfo {

    int getBookPages();

    String getBookUrlPdf();

    String getBookDescription();

    CategoryBookNameEntityInfo getCategoryBookEntity();
}