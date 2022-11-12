package com.spacecodee.library_book_backend.dto.rating.book.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRatingByIdDto implements Serializable {

    private int bookId;
    private float ratingBook;
    private float ratingPromedioBook;
    private String bookName;
    private int pages;
    private String author;
    private String image;
    private String pdf;
    private String description;
    private String categoryName;
}