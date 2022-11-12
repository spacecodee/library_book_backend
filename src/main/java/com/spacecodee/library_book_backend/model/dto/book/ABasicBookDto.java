package com.spacecodee.library_book_backend.model.dto.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
@Getter
@Setter
public abstract class ABasicBookDto implements Serializable {
    @NotEmpty(message = "{not.empty.book.name}")
    protected String name;
    @NotEmpty(message = "{not.empty.book.author}")
    protected String author;
    @NotEmpty(message = "{not.empty.book.url.image}")
    protected String image;
}