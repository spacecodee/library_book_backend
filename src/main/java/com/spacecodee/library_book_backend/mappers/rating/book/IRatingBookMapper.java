package com.spacecodee.library_book_backend.mappers.rating.book;

import com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRatingBookMapper {

    IRatingBookMapper INSTANCE = Mappers.getMapper(IRatingBookMapper.class);

    @Mapping(target = "ratingBook", source = "ratingBook")
    @Mapping(target = "userRatingBookId", ignore = true)
    @Mapping(target = "bookEntity", ignore = true)
    @Mapping(target = "userClientEntity", ignore = true)
    UserRatingBookEntity toEntity(RatingBookVo vo);

    @AfterMapping
    default void setBookAndClientId(@MappingTarget UserRatingBookEntity entity, RatingBookVo vo) {
        entity.setUserRatingBookId(new UserRatingBookKeyEntity(vo.getBookId(), vo.getClientId()));
    }
}
