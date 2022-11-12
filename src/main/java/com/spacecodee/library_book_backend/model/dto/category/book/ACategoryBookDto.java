package com.spacecodee.library_book_backend.model.dto.category.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity} entity
 */
@Getter
@Setter
public abstract class ACategoryBookDto implements Serializable {
    @NotEmpty(message = "{not.empty.category.book}")
    protected String name;
}