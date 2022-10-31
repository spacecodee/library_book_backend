package com.spacecodee.library_book_backend.dto.book;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty(message = "Book name is required")
    private String name;
    @NotNull(message = "Book pages is required")
    private int pages;
    @NotEmpty(message = "Book author is required")
    private String author;
    @NotEmpty(message = "Book url image is required")
    private String urlImage;
    @NotEmpty(message = "Book url pdf is required")
    private String urlPdf;
    @NotEmpty(message = "Book description is required")
    private String description;
}