package com.spacecodee.library_book_backend.entity.book.projections;

import com.spacecodee.library_book_backend.entity.book.BookEntity;

/**
 * A Projection for the {@link BookEntity} entity
 */
public interface BookIdNameAuthorImageEntityInfo {

    int getBookId();

    String getBookName();

    String getBookAuthor();

    String getBookUrlImage();
}