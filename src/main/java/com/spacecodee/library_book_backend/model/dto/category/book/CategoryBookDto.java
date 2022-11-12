package com.spacecodee.library_book_backend.model.dto.category.book;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryBookDto extends ACategoryBookDto implements Serializable {

    private int id;
}