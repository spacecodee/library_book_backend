package com.spacecodee.library_book_backend.mappers.rating.book;

import com.spacecodee.library_book_backend.dto.rating.book.read.GetRatingByIdDto;
import com.spacecodee.library_book_backend.entity.book.projections.BookAndCategoryNameEntityInfo;
import com.spacecodee.library_book_backend.entity.rating.projections.RatingAndBookEntityInfo;
import com.spacecodee.library_book_backend.entity.rating.projections.RatingBookEntityInfo;
import com.spacecodee.library_book_backend.model.dto.rating.book.RatingPromedioBookDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRatingBookReadMapper {

    IRatingBookReadMapper INSTANCE = Mappers.getMapper(IRatingBookReadMapper.class);

    default RatingPromedioBookDto getPromedio(@NotNull Set<RatingBookEntityInfo> rating) {
        var promedio = rating.stream().mapToDouble(RatingBookEntityInfo::getRatingBook).average().orElse(0);
        return new RatingPromedioBookDto(promedio);
    }

    @Mapping(target = "ratingBook", source = "ratingBook")
    @Mapping(target = "bookId", source = "bookEntity", qualifiedByName = "setBookId")
    @Mapping(target = "bookName", source = "bookEntity", qualifiedByName = "setBookName")
    @Mapping(target = "author", source = "bookEntity", qualifiedByName = "setAuthor")
    @Mapping(target = "pages", source = "bookEntity", qualifiedByName = "setPages")
    @Mapping(target = "image", source = "bookEntity", qualifiedByName = "setImage")
    @Mapping(target = "pdf", source = "bookEntity", qualifiedByName = "setPdf")
    @Mapping(target = "description", source = "bookEntity", qualifiedByName = "setDescription")
    @Mapping(target = "categoryName", source = "bookEntity", qualifiedByName = "setCategoryName")
    @Mapping(target = "ratingPromedioBook", ignore = true)
    GetRatingByIdDto toDto(RatingAndBookEntityInfo info);

    @Named("setBookId")
    default int setBookId(@NotNull BookAndCategoryNameEntityInfo bookEntity) {
        return bookEntity.getBookId();
    }

    @Named("setBookName")
    default String setBookName(@NotNull BookAndCategoryNameEntityInfo bookEntity) {
        return bookEntity.getBookName();
    }

    @Named("setAuthor")
    default String setAuthor(@NotNull BookAndCategoryNameEntityInfo bookEntity) {
        return bookEntity.getBookAuthor();
    }

    @Named("setPages")
    default int setPages(@NotNull BookAndCategoryNameEntityInfo bookEntity) {
        return bookEntity.getBookPages();
    }

    @Named("setImage")
    default String setImage(@NotNull BookAndCategoryNameEntityInfo bookEntity) {
        return bookEntity.getBookUrlImage();
    }

    @Named("setPdf")
    default String setPdf(@NotNull BookAndCategoryNameEntityInfo bookEntity) {
        return bookEntity.getBookUrlPdf();
    }

    @Named("setDescription")
    default String setDescription(@NotNull BookAndCategoryNameEntityInfo bookEntity) {
        return bookEntity.getBookDescription();
    }

    @Named("setCategoryName")
    default String setCategoryName(@NotNull BookAndCategoryNameEntityInfo bookEntity) {
        return bookEntity.getCategoryBookEntity().getCategoryBookName();
    }
}
