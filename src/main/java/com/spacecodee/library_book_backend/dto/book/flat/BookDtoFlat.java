package com.spacecodee.library_book_backend.dto.book.flat;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookDtoFlat extends GetIdNameAuthorImagePromedioRatingBookDto implements Serializable {

    private String pdf;
    private int pages;
    private String description;
    private String categoryName;
    private double rating;
}
