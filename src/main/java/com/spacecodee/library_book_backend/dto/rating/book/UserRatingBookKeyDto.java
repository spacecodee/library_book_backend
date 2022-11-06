package com.spacecodee.library_book_backend.dto.rating.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRatingBookKeyDto implements Serializable {

    private int clientId;
    private int bookId;
}