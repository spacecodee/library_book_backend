package com.spacecodee.library_book_backend.mappers.category.book;

import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import com.spacecodee.library_book_backend.model.vo.category.book.CategoryBookVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryBookMapper {

    ICategoryBookMapper INSTANCE = Mappers.getMapper(ICategoryBookMapper.class);

    @Mapping(target = "categoryBookName", source = "name")
    @Mapping(target = "categoryBookId", source = "id")
    @Mapping(target = "booksEntity", ignore = true)
    CategoryBookEntity toEntity(CategoryBookVo aDto);
}
