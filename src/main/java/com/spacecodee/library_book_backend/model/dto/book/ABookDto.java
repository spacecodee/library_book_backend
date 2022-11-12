package com.spacecodee.library_book_backend.model.dto.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
@Getter
@Setter
public abstract class ABookDto extends ABasicBookDto implements Serializable {
    @Positive(message = "{positive.book.pages}")
    protected int pages;
    @NotEmpty(message = "{not.empty.book.url.pdf}")
    protected String pdf;
    @NotEmpty(message = "{not.empty.book.description}")
    protected String description;
}