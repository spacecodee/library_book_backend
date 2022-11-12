package com.spacecodee.library_book_backend.model.vo.book;

import com.spacecodee.library_book_backend.model.dto.book.ABookDto;
import com.spacecodee.library_book_backend.model.vo.category.book.CategoryBookVo;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.book.BookEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookVo extends ABookDto implements Serializable {
    private int id;
    private CategoryBookVo categoryBookVo;
}