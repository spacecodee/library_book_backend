package com.spacecodee.library_book_backend.mappers.rating.book;

import com.spacecodee.library_book_backend.entity.UserClientEntity;
import com.spacecodee.library_book_backend.entity.book.BookEntity;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity;
import com.spacecodee.library_book_backend.mappers.book.IBookMapper;
import com.spacecodee.library_book_backend.mappers.user.client.IUserClientMapper;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookVo;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRatingBookMapper {

    IRatingBookMapper INSTANCE = Mappers.getMapper(IRatingBookMapper.class);

    @Mapping(target = "ratingBook", source = "ratingBook")
    @Mapping(target = "userRatingBookId", ignore = true)
    @Mapping(target = "bookEntity", source = "bookId", qualifiedByName = "bookEntity")
    @Mapping(target = "userClientEntity", source = "clientId", qualifiedByName = "userClientEntity")
    UserRatingBookEntity toEntity(RatingBookVo vo);

    @AfterMapping
    default void setBookAndClientId(@MappingTarget @NotNull UserRatingBookEntity entity, @NotNull RatingBookVo vo) {
        entity.setUserRatingBookId(new UserRatingBookKeyEntity(vo.getClientId(), vo.getBookId()));
    }

    @Named("bookEntity")
    default BookEntity setBookEntity(int id) {
        return IBookMapper.INSTANCE.mapId(id);
    }

    @Named("userClientEntity")
    default UserClientEntity setUserClientEntity(int id) {
        return IUserClientMapper.INSTANCE.mapId(id);
    }
}
