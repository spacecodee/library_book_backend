package com.spacecodee.library_book_backend.dto.book;

import lombok.*;

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

    private String name;
    private int pages;
    private String author;
    private String urlImage;
    private String urlPdf;
    private String description;
}