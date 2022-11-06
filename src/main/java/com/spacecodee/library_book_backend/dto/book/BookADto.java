package com.spacecodee.library_book_backend.dto.book;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.BookEntity} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BookADto implements Serializable {

    @NotEmpty(message = "{not.empty.book.name}")
    private String name;
    @NotEmpty(message = "{not.empty.book.pages}")
    @Positive(message = "{positive.book.pages}")
    private int pages;
    @NotEmpty(message = "{not.empty.book.author}")
    private String author;
    @NotEmpty(message = "{not.empty.book.url.image}")
    private String urlImage;
    @NotEmpty(message = "{not.empty.book.url.pdf}")
    private String urlPdf;
    @NotEmpty(message = "{not.empty.book.description}")
    private String description;
}