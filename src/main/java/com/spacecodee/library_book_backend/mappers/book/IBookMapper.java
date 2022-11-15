package com.spacecodee.library_book_backend.mappers.book;

import com.spacecodee.library_book_backend.entity.book.BookEntity;
import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import com.spacecodee.library_book_backend.mappers.category.book.ICategoryBookMapper;
import com.spacecodee.library_book_backend.model.vo.book.BookVo;
import com.spacecodee.library_book_backend.model.vo.category.book.CategoryBookVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookMapper {

    IBookMapper INSTANCE = Mappers.getMapper(IBookMapper.class);

    @Mapping(target = "bookId", source = "id")
    @Mapping(target = "bookName", source = "name")
    @Mapping(target = "bookAuthor", source = "author")
    @Mapping(target = "bookUrlImage", source = "image")
    @Mapping(target = "bookPages", source = "pages")
    @Mapping(target = "bookUrlPdf", source = "pdf")
    @Mapping(target = "bookDescription", source = "description")
    @Mapping(target = "categoryBookEntity", source = "categoryBookVo", qualifiedByName = "categoryBookEntity")
    @Mapping(target = "ratingBooksEntity", ignore = true)
    BookEntity toEntity(BookVo vo);

    default BookEntity mapId(int id) {
        return new BookEntity(id);
    }

    @Named("categoryBookEntity")
    default CategoryBookEntity setCategoryEntity(CategoryBookVo vo) {
        return ICategoryBookMapper.INSTANCE.toEntity(vo);
    }

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "categoryBookVo", source = "categoryBookEntity", qualifiedByName = "categoryBookVo")
    BookVo toDto(BookEntity entity);

    @Named("categoryBookVo")
    default CategoryBookVo setCategoryVo(CategoryBookEntity entity) {
        return ICategoryBookMapper.INSTANCE.toVo(entity);
    }
}
