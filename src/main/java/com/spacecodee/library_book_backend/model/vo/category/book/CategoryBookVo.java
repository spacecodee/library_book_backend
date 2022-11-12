package com.spacecodee.library_book_backend.model.vo.category.book;

import com.spacecodee.library_book_backend.model.dto.category.book.ACategoryBookDto;
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
public class CategoryBookVo extends ACategoryBookDto implements Serializable {
    private int id;
}