package com.spacecodee.library_book_backend.dto.category.book;

import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * A DTO for the {@link CategoryBookEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CategoryBookADto implements Serializable {
    @NotEmpty(message = "{not.empty.category.book}")
    private String name;
}