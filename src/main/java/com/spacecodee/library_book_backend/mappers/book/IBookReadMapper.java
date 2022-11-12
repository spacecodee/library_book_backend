package com.spacecodee.library_book_backend.mappers.book;

import com.spacecodee.library_book_backend.entity.book.projections.BookIdNameAuthorImageRatingsEntityInfo;
import com.spacecodee.library_book_backend.entity.rating.projections.RatingBookEntityInfo;
import com.spacecodee.library_book_backend.mappers.rating.book.IRatingBookReadMapper;
import com.spacecodee.library_book_backend.model.dto.book.BookAndRatingPromedioDto;
import com.spacecodee.library_book_backend.model.dto.rating.book.RatingPromedioBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookReadMapper {

    IBookReadMapper INSTANCE = Mappers.getMapper(IBookReadMapper.class);

    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "image", source = "bookUrlImage")
    @Mapping(target = "name", source = "bookName")
    @Mapping(target = "author", source = "bookAuthor")
    @Mapping(target = "ratingPromedioBookDto", source = "ratingBooksEntity", qualifiedByName = "ratingPromedioBookDto")
    BookAndRatingPromedioDto toDto(BookIdNameAuthorImageRatingsEntityInfo entity);

    default Set<BookAndRatingPromedioDto> getBooks(Set<BookIdNameAuthorImageRatingsEntityInfo> entity) {
        var books = new HashSet<BookAndRatingPromedioDto>();
        entity.forEach(book -> books.add(this.toDto(book)));
        return books;
    }

    @Named("ratingPromedioBookDto")
    default RatingPromedioBookDto getRatingPromedio(Set<RatingBookEntityInfo> entity) {
        return IRatingBookReadMapper.INSTANCE.getPromedio(entity);
    }
}
