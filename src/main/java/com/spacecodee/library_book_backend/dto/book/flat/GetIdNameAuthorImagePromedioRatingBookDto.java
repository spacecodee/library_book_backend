package com.spacecodee.library_book_backend.dto.book.flat;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
public class GetIdNameAuthorImagePromedioRatingBookDto implements Serializable {

    protected int id;
    protected String name;
    protected String author;
    protected String image;
    protected double promedioRating;
}
