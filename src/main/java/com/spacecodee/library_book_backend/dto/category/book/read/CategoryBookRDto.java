package com.spacecodee.library_book_backend.dto.category.book.read;

import com.spacecodee.library_book_backend.dto.book.flat.GetIdNameAuthorImagePromedioRatingBookDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
public class CategoryBookRDto {

    private int id;
    private String name;
    private Set<GetIdNameAuthorImagePromedioRatingBookDto> getALlBookDto;
}
