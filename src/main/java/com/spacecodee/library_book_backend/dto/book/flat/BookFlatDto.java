package com.spacecodee.library_book_backend.dto.book.flat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.BookEntity} entity
 */
@Getter()
@Setter()
@EqualsAndHashCode()
@ToString()
public abstract class BookFlatDto implements Serializable {

    @NotEmpty(message = "{not.empty.book.name}")
    private String name;
    @Positive(message = "{positive.book.pages}")
    private int pages;
    @NotEmpty(message = "{not.empty.book.author}")
    private String author;
    @NotEmpty(message = "{not.empty.book.url.image}")
    private String image;
    @NotEmpty(message = "{not.empty.book.url.pdf}")
    private String pdf;
    @NotEmpty(message = "{not.empty.book.description}")
    private String description;
}