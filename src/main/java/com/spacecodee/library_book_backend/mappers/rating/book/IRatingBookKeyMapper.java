package com.spacecodee.library_book_backend.mappers.rating.book;

import com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookKeyVo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRatingBookKeyMapper {

    IRatingBookKeyMapper INSTANCE = Mappers.getMapper(IRatingBookKeyMapper.class);

    @Mapping(source = "clientId", target = "ratingUserId")
    @Mapping(source = "bookId", target = "ratingBookId")
    UserRatingBookKeyEntity toEntity(RatingBookKeyVo dto);

    @InheritInverseConfiguration(name = "toEntity")
    RatingBookKeyVo toDto(UserRatingBookKeyEntity entity);

    default RatingBookKeyVo toDto(int clientId, int bookId) {
        return new RatingBookKeyVo(clientId, bookId);
    }

    @Mapping(target = "ratingUserId", source = "clientId")
    @Mapping(target = "ratingBookId", source = "bookId")
    default UserRatingBookKeyEntity toEntity(int clientId, int bookId) {
        return new UserRatingBookKeyEntity(clientId, bookId);
    }
}
