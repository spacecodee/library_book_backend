package com.spacecodee.library_book_backend.mappers.book;

import com.spacecodee.library_book_backend.dto.book.BookDto;
import com.spacecodee.library_book_backend.dto.book.BookUDto;
import com.spacecodee.library_book_backend.dto.book.flat.BookFlatADto;
import com.spacecodee.library_book_backend.dto.book.flat.BookFlatUDto;
import com.spacecodee.library_book_backend.entity.BookEntity;
import com.spacecodee.library_book_backend.mappers.category.book.ICategoryBookEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookEntityMapper {

    IBookEntityMapper INSTANCE = Mappers.getMapper(IBookEntityMapper.class);

    //update book
    @Mapping(source = "id", target = "bookId")
    @Mapping(source = "name", target = "bookName")
    @Mapping(source = "pages", target = "bookPages")
    @Mapping(source = "author", target = "bookAuthor")
    @Mapping(source = "image", target = "bookUrlImage")
    @Mapping(source = "pdf", target = "bookUrlPdf")
    @Mapping(source = "description", target = "bookDescription")
    @Mapping(target = "categoryBookEntity", ignore = true)
    BookEntity uDtoToEntity(BookUDto dto);

    @InheritInverseConfiguration(name = "uDtoToEntity")
    BookUDto entityToUDto(BookEntity entity);

    //list dto
    @InheritInverseConfiguration(name = "entityToUDto")
    BookEntity lDtoToEntity(BookDto bookDto);

    @AfterMapping
    default void linkCategoryBookEntity(@MappingTarget BookEntity entity, BookDto dto) {
        entity.setCategoryBookEntity(ICategoryBookEntityMapper.INSTANCE.uDtoToEntity(dto.getCategoryBookDto()));
    }

    @InheritInverseConfiguration(name = "lDtoToEntity")
    BookDto entityToLDto(BookEntity bookEntity);

    @AfterMapping
    default void linkCategoryBookDto(@MappingTarget BookDto dto, BookEntity entity) {
        dto.setCategoryBookDto(ICategoryBookEntityMapper.INSTANCE.entityToUDto(entity.getCategoryBookEntity()));
    }

    //flat u dto
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "pages", target = "pages")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "pdf", target = "pdf")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "categoryBookDto", ignore = true)
    BookDto toLDto(BookFlatUDto dto);

    //flat a dto
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryBookDto", ignore = true)
    @InheritConfiguration(name = "toLDto")
    BookDto toLDto(BookFlatADto dto);
}
