package com.spacecodee.library_book_backend.mappers.category.book;

import com.spacecodee.library_book_backend.dto.book.BookUDto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookADto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookLDto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookUDto;
import com.spacecodee.library_book_backend.entity.BookEntity;
import com.spacecodee.library_book_backend.entity.CategoryBookEntity;
import com.spacecodee.library_book_backend.mappers.book.IBookEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryBookEntityMapper {

    ICategoryBookEntityMapper INSTANCE = Mappers.getMapper(ICategoryBookEntityMapper.class);

    //add new entity
    @Mapping(source = "name", target = "categoryBookName")
    @Mapping(target = "booksEntity", ignore = true)
    @Mapping(target = "categoryBookId", ignore = true)
    CategoryBookEntity aDtoToEntity(CategoryBookADto aDto);

    @InheritInverseConfiguration(name = "aDtoToEntity")
    CategoryBookADto entityToADto(CategoryBookEntity entity);

    @InheritInverseConfiguration(name = "entityToADto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryBookEntity updateEntityFromADto(CategoryBookADto aDto, @MappingTarget CategoryBookEntity entity);

    //update entity
    @InheritInverseConfiguration(name = "entityToADto")
    @Mapping(source = "id", target = "categoryBookId")
    CategoryBookEntity uDtoToEntity(CategoryBookUDto uDto);

    @InheritInverseConfiguration(name = "uDtoToEntity")
    CategoryBookUDto entityToUDto(CategoryBookEntity entity);

    @InheritInverseConfiguration(name = "entityToUDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryBookEntity updateEntityFromUDto(CategoryBookUDto uDto, @MappingTarget CategoryBookEntity entity);

    //list entity
    @InheritInverseConfiguration(name = "entityToUDto")
    CategoryBookEntity lDtoToEntity(CategoryBookLDto lDto);

    @AfterMapping
    default void linkBooksLDtoToEntity(@MappingTarget CategoryBookEntity entity, CategoryBookLDto dto) {
        final Set<BookEntity> booksEntity = new HashSet<>();
        dto.getBooksDto().forEach(bookDto -> booksEntity.add(IBookEntityMapper.INSTANCE.uDtoToEntity(bookDto)));
        entity.setBooksEntity(booksEntity);
    }

    @InheritInverseConfiguration(name = "lDtoToEntity")
    CategoryBookLDto entityToLDto(CategoryBookEntity entity);

    @AfterMapping
    default void linkBooksEntityToLDto(@MappingTarget CategoryBookLDto dto, CategoryBookEntity entity) {
        final Set<BookUDto> booksDto = new HashSet<>();
        entity.getBooksEntity().forEach(bookDto -> booksDto.add(IBookEntityMapper.INSTANCE.entityToUDto(bookDto)));
        dto.setBooksDto(booksDto);
    }

    @InheritInverseConfiguration(name = "entityToLDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryBookEntity updateEntityLDto(CategoryBookLDto lDto, @MappingTarget CategoryBookEntity entity);
}
