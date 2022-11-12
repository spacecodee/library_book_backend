package com.spacecodee.library_book_backend.model.vo.rating.book;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RatingBookVo implements Serializable {
    private int clientId;
    private int bookId;
    private short ratingBook;
}