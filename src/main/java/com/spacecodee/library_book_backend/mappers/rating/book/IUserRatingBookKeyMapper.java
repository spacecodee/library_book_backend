package com.spacecodee.library_book_backend.mappers.rating.book;

import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookKeyDto;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserRatingBookKeyMapper {

    IUserRatingBookKeyMapper INSTANCE = Mappers.getMapper(IUserRatingBookKeyMapper.class);

    @Mapping(source = "clientId", target = "ratingUserId")
    @Mapping(source = "bookId", target = "ratingBookId")
    UserRatingBookKeyEntity toEntity(UserRatingBookKeyDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    UserRatingBookKeyDto toDto(UserRatingBookKeyEntity entity);

    @InheritInverseConfiguration(name = "toDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserRatingBookKeyEntity updateEntityFromDto(UserRatingBookKeyDto dto,
                                                @MappingTarget UserRatingBookKeyEntity entity);
}
