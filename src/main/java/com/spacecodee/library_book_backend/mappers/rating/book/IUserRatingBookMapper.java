package com.spacecodee.library_book_backend.mappers.rating.book;

import com.spacecodee.library_book_backend.dto.rating.book.RatingBookDto;
import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookDto;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookKeyEntity;
import com.spacecodee.library_book_backend.mappers.book.IBookEntityMapper;
import com.spacecodee.library_book_backend.mappers.user.client.IUserClientMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserRatingBookMapper {

    IUserRatingBookMapper INSTANCE = Mappers.getMapper(IUserRatingBookMapper.class);

    @Mapping(target = "userRatingBookId", ignore = true)
    @Mapping(source = "ratingBook", target = "ratingBook")
    @Mapping(target = "userClientEntity", ignore = true)
    @Mapping(target = "bookEntity", ignore = true)
    UserRatingBookEntity dtoToEntity(UserRatingBookDto dto);

    @AfterMapping
    default void setBookEntity(@MappingTarget UserRatingBookEntity entity, UserRatingBookDto dto) {
        entity.setBookEntity(IBookEntityMapper.INSTANCE.uDtoToEntity(dto.getBookDto()));
    }

    @AfterMapping
    default void setUserClientEntity(@MappingTarget UserRatingBookEntity entity, UserRatingBookDto dto) {
        entity.setUserClientEntity(IUserClientMapper.INSTANCE.dtoToEntity(dto.getUserClientDto()));
    }

    @AfterMapping
    default void setId(@MappingTarget UserRatingBookEntity entity, UserRatingBookDto dto) {
        if (dto.getBookDto() != null && dto.getUserClientDto() != null
                && dto.getBookDto().getId() != 0 && dto.getUserClientDto().getId() != 0) {
            final UserRatingBookKeyEntity keyEntity
                    = new UserRatingBookKeyEntity(dto.getUserClientDto().getId(), dto.getBookDto().getId());
            entity.setUserRatingBookId(keyEntity);
        }
    }

    @InheritInverseConfiguration(name = "dtoToEntity")
    UserRatingBookDto entityToDto(UserRatingBookEntity entity);


    @AfterMapping
    default void setBookDto(@MappingTarget UserRatingBookDto dto, UserRatingBookEntity entity) {
        dto.setBookDto(IBookEntityMapper.INSTANCE.entityToUDto(entity.getBookEntity()));
    }

    @AfterMapping
    default void setUserClientDto(@MappingTarget UserRatingBookDto dto, UserRatingBookEntity entity) {
        dto.setUserClientDto(IUserClientMapper.INSTANCE.entityToDto(entity.getUserClientEntity()));
    }

    @InheritInverseConfiguration(name = "entityToDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserRatingBookEntity updateEntityFromDto(UserRatingBookDto dto, @MappingTarget UserRatingBookEntity entity);

    @Mapping(source = "ratingBook", target = "ratingBook")
    @Mapping(target = "bookDto", ignore = true)
    @Mapping(target = "userClientDto", ignore = true)
    UserRatingBookDto pDtoToDto(RatingBookDto dto);
}
