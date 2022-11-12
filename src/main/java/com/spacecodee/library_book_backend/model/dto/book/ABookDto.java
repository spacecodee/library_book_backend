package com.spacecodee.library_book_backend.model.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
@Getter
@Setter
public abstract class ABookDto extends ABasicBookDto implements Serializable {
    protected int pages;
    protected String pdf;
    protected String description;
}