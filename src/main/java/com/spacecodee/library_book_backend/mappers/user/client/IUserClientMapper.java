package com.spacecodee.library_book_backend.mappers.user.client;

import com.spacecodee.library_book_backend.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.entity.UserClientEntity;
import com.spacecodee.library_book_backend.mappers.people.IPeopleMapper;
import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserClientMapper {

    IUserClientMapper INSTANCE = Mappers.getMapper(IUserClientMapper.class);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "userEmail")
    @Mapping(source = "password", target = "userPassword")
    @Mapping(target = "userRolEntity", ignore = true)
    @Mapping(target = "peopleEntity", ignore = true)
    UserClientEntity dtoToEntity(UserClientDto dto);

    @AfterMapping
    default void setPeopleEntity(@MappingTarget UserClientEntity entity, UserClientDto userClientDto) {
        if (userClientDto.getPeopleDto() != null) {
            entity.setPeopleEntity(IPeopleMapper.INSTANCE.dtoToEntity(userClientDto.getPeopleDto()));
        }
    }

    @AfterMapping
    default void setRoleEntity(@MappingTarget UserClientEntity entity, UserClientDto userClientDto) {
        if (userClientDto.getUserRolDto() != null) {
            entity.setUserRolEntity(IUserRoleMapper.INSTANCE.dtoToEntity(userClientDto.getUserRolDto()));
        }
    }

    @InheritInverseConfiguration(name = "dtoToEntity")
    UserClientDto entityToDto(UserClientEntity entity);


    @AfterMapping
    default void setPeopleDto(@MappingTarget UserClientDto userClientDto, UserClientEntity entity) {
        if (entity.getPeopleEntity() != null) {
            userClientDto.setPeopleDto(IPeopleMapper.INSTANCE.entityToDto(entity.getPeopleEntity()));
        }
    }

    @AfterMapping
    default void setRoleDto(@MappingTarget UserClientDto userClientDto, UserClientEntity entity) {
        if (entity.getUserRolEntity() != null) {
            userClientDto.setUserRolDto(IUserRoleMapper.INSTANCE.entityToDto(entity.getUserRolEntity()));
        }
    }


    @InheritInverseConfiguration(name = "entityToDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserClientEntity updateEntityFromDto(UserClientDto dto, @MappingTarget UserClientEntity entity);
}
