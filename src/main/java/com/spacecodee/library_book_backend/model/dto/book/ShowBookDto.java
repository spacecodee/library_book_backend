package com.spacecodee.library_book_backend.model.dto.book;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ShowBookDto extends BookDto {

    private String categoryName;
    private float globalRating;
    private float rating;
}
