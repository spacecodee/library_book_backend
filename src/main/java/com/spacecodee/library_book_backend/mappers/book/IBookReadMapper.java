package com.spacecodee.library_book_backend.mappers.book;

import com.spacecodee.library_book_backend.entity.book.BookEntity;
import com.spacecodee.library_book_backend.entity.book.projections.BookAndCategoryAndRatingsEntityInfo;
import com.spacecodee.library_book_backend.entity.book.projections.BookIdNameAuthorImageRatingsEntityInfo;
import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import com.spacecodee.library_book_backend.entity.category.book.projections.CategoryBookNameEntityInfo;
import com.spacecodee.library_book_backend.entity.rating.projections.RatingBookEntityInfo;
import com.spacecodee.library_book_backend.mappers.category.book.ICategoryBookReadMapper;
import com.spacecodee.library_book_backend.mappers.rating.book.IRatingBookReadMapper;
import com.spacecodee.library_book_backend.model.dto.book.BookAndCategoryDto;
import com.spacecodee.library_book_backend.model.dto.book.BookAndRatingPromedioDto;
import com.spacecodee.library_book_backend.model.dto.book.ShowBookDto;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookDto;
import com.spacecodee.library_book_backend.model.dto.rating.book.RatingPromedioBookDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookReadMapper {

    IBookReadMapper INSTANCE = Mappers.getMapper(IBookReadMapper.class);

    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "name", source = "bookName")
    @Mapping(target = "author", source = "bookAuthor")
    @Mapping(target = "image", source = "bookUrlImage")
    @Mapping(target = "ratingPromedioBookDto", source = "ratingBooksEntity", qualifiedByName = "ratingPromedioBookDto")
    BookAndRatingPromedioDto toDto(BookIdNameAuthorImageRatingsEntityInfo entity);

    @Named("ratingPromedioBookDto")
    default RatingPromedioBookDto setRatingPromedio(Set<RatingBookEntityInfo> entity) {
        return IRatingBookReadMapper.INSTANCE.getPromedio(entity);
    }

    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "name", source = "bookName")
    @Mapping(target = "pages", source = "bookPages")
    @Mapping(target = "image", source = "bookUrlImage")
    @Mapping(target = "author", source = "bookAuthor")
    @Mapping(target = "description", source = "bookDescription")
    @Mapping(target = "pdf", source = "bookUrlPdf")
    @Mapping(target = "categoryName", source = "categoryBookEntity", qualifiedByName = "categoryName")
    @Mapping(target = "globalRating", source = "ratingBooksEntity", qualifiedByName = "globalRating")
    @Mapping(target = "rating", source = "ratingBooksEntity", ignore = true)
    ShowBookDto toDto(BookAndCategoryAndRatingsEntityInfo entity);

    @Named("globalRating")
    default float setGlobalRating(Set<RatingBookEntityInfo> entity) {
        return IRatingBookReadMapper.INSTANCE.getGlobalRating(entity);
    }

    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "name", source = "bookName")
    @Mapping(target = "pages", source = "bookPages")
    @Mapping(target = "image", source = "bookUrlImage")
    @Mapping(target = "author", source = "bookAuthor")
    @Mapping(target = "description", source = "bookDescription")
    @Mapping(target = "pdf", source = "bookUrlPdf")
    @Mapping(target = "categoryBookDto", source = "categoryBookEntity", qualifiedByName = "categoryBookDto")
    BookAndCategoryDto toDto(BookEntity entity);

    default List<BookAndCategoryDto> setBooksAndCategoryDto(@NotNull List<BookEntity> entity) {
        final var booksWithRating = new ArrayList<BookAndCategoryDto>();
        entity.stream().map(this::toDto).forEach(booksWithRating::add);
        return booksWithRating;
    }

    default Set<BookAndRatingPromedioDto> setBooksRatingPromedio(
            @NotNull Set<BookIdNameAuthorImageRatingsEntityInfo> entity) {
        final var books = new HashSet<BookAndRatingPromedioDto>();
        entity.stream().map(this::toDto).forEach(books::add);
        return books;
    }


    default List<BookAndRatingPromedioDto> getBooks(@NotNull List<BookIdNameAuthorImageRatingsEntityInfo> entities) {
        final var books = new ArrayList<BookAndRatingPromedioDto>();
        entities.stream().map(this::toDto).forEach(books::add);
        return books;
    }

    @Named("categoryBookDto")
    default CategoryBookDto categoryBookDto(CategoryBookEntity entity) {
        return ICategoryBookReadMapper.INSTANCE.toDto(entity);
    }

    @Named("categoryName")
    default String setCategoryBook(@NotNull CategoryBookNameEntityInfo entity) {
        return entity.getCategoryBookName();
    }
}
