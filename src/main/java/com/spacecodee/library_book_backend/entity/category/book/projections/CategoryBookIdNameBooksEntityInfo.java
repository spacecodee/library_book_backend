package com.spacecodee.library_book_backend.entity.category.book.projections;

import com.spacecodee.library_book_backend.entity.book.projections.BookIdNameAuthorImageRatingsEntityInfo;
import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;

import java.util.Set;

/**
 * A Projection for the {@link CategoryBookEntity} entity
 */
public interface CategoryBookIdNameBooksEntityInfo extends CategoryBookNameEntityInfo {
    int getCategoryBookId();

    Set<BookIdNameAuthorImageRatingsEntityInfo> getBooksEntity();
}