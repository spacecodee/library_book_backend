package com.spacecodee.library_book_backend.dto.category.book;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.CategoryBookEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CategoryBookADto implements Serializable {
    @NotNull(message = "Category name is required")
    @NotEmpty(message = "Category name is required")
    private String name;
}