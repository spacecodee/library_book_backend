package com.spacecodee.library_book_backend.model.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
@Getter
@Setter
public abstract class ABasicBookDto implements Serializable {
    protected String name;
    protected String author;
    protected String image;
}