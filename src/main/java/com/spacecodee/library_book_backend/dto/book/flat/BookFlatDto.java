package com.spacecodee.library_book_backend.dto.book.flat;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
@Data
@Accessors(chain = true)
public abstract class BookFlatDto implements Serializable {
    protected String name;
    private int pages;
    private String author;
    private String image;
    private String pdf;
    private String description;
}