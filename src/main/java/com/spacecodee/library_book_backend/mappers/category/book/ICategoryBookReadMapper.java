package com.spacecodee.library_book_backend.mappers.category.book;

import com.spacecodee.library_book_backend.entity.book.projections.BookIdNameAuthorImageRatingsEntityInfo;
import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import com.spacecodee.library_book_backend.entity.category.book.projections.CategoryBookIdNameBooksEntityInfo;
import com.spacecodee.library_book_backend.mappers.book.IBookReadMapper;
import com.spacecodee.library_book_backend.model.dto.book.BookAndRatingPromedioDto;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookAndBookDto;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryBookReadMapper {

    ICategoryBookReadMapper INSTANCE = Mappers.getMapper(ICategoryBookReadMapper.class);

    @Mapping(target = "id", source = "categoryBookId")
    @Mapping(target = "name", source = "categoryBookName")
    CategoryBookDto toDto(CategoryBookEntity categoryBookEntity);

    @Mapping(target = "id", source = "categoryBookId")
    @Mapping(target = "name", source = "categoryBookName")
    @Mapping(target = "bookDto", source = "booksEntity", qualifiedByName = "bookDto")
    CategoryBookAndBookDto toRDto(CategoryBookIdNameBooksEntityInfo entity);

    @Named("bookDto")
    default Set<BookAndRatingPromedioDto> setBooks(Set<BookIdNameAuthorImageRatingsEntityInfo> entity) {
        return IBookReadMapper.INSTANCE.getBooks(entity);
    }
}
