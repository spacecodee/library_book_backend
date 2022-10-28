package com.spacecodee.library_book_backend.dto.category.book;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.CategoryBookEntity} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryBookUDto extends CategoryBookADto implements Serializable {
    @NotNull(message = "Category id is required")
    private int id;
}