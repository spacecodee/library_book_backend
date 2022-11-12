package com.spacecodee.library_book_backend.dto.category.book;

import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link CategoryBookEntity} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryBookUDto extends CategoryBookADto implements Serializable {
    private int id;
}