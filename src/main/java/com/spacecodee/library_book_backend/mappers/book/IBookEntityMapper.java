package com.spacecodee.library_book_backend.mappers.book;

import com.spacecodee.library_book_backend.dto.book.BookADto;
import com.spacecodee.library_book_backend.dto.book.BookLDto;
import com.spacecodee.library_book_backend.dto.book.BookUDto;
import com.spacecodee.library_book_backend.entity.BookEntity;
import com.spacecodee.library_book_backend.mappers.category.book.ICategoryBookEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookEntityMapper {

    IBookEntityMapper INSTANCE = Mappers.getMapper(IBookEntityMapper.class);

    //add book
    @Mapping(target = "bookId", ignore = true)
    @Mapping(target = "categoryBookEntity", ignore = true)
    @Mapping(source = "name", target = "bookName")
    @Mapping(source = "pages", target = "bookPages")
    @Mapping(source = "author", target = "bookAuthor")
    @Mapping(source = "urlImage", target = "bookUrlImage")
    @Mapping(source = "urlPdf", target = "bookUrlPdf")
    @Mapping(source = "description", target = "bookDescription")
    BookEntity aDtoToEntity(BookADto aDto);

    @InheritInverseConfiguration(name = "aDtoToEntity")
    BookADto entityToADto(BookEntity entity);

    @InheritInverseConfiguration(name = "entityToADto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookEntity updateEntityFromADto(BookADto aDto, @MappingTarget BookEntity entity);

    //update book
    @Mapping(source = "id", target = "bookId")
    @InheritInverseConfiguration(name = "entityToADto")
    BookEntity uDtoToEntity(BookUDto dto);

    @InheritInverseConfiguration(name = "uDtoToEntity")
    BookUDto entityToUDto(BookEntity entity);

    @InheritInverseConfiguration(name = "entityToUDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookEntity updateEntityFromUDto(BookUDto dto, @MappingTarget BookEntity entity);

    //list dto
    @InheritInverseConfiguration(name = "entityToUDto")
    BookEntity lDtoToEntity(BookLDto bookLDto);

    @AfterMapping
    default void linkCategoryBookEntity(@MappingTarget BookEntity entity, BookLDto dto) {
        entity.setCategoryBookEntity(ICategoryBookEntityMapper.INSTANCE.uDtoToEntity(dto.getCategoryBookDto()));
    }

    @InheritInverseConfiguration(name = "lDtoToEntity")
    BookLDto entityToLDto(BookEntity bookEntity);

    @AfterMapping
    default void linkCategoryBookDto(@MappingTarget BookLDto dto, BookEntity entity) {
        dto.setCategoryBookDto(ICategoryBookEntityMapper.INSTANCE.entityToUDto(entity.getCategoryBookEntity()));
    }

    @InheritInverseConfiguration(name = "entityToLDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookEntity updateEntityLDto(BookLDto bookLDto, @MappingTarget BookEntity bookEntity);
}
