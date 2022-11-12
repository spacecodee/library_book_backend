package com.spacecodee.library_book_backend.model.dto.rating.book;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
@Getter
@Setter
public abstract class ARatingBookDto implements Serializable {
    private double rating;
}