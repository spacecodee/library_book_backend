package com.spacecodee.library_book_backend.mappers.user.system;

import com.spacecodee.library_book_backend.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.entity.UserSystemEntity;
import com.spacecodee.library_book_backend.mappers.people.IPeopleMapper;
import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserSystemMapper {

    IUserSystemMapper INSTANCE = Mappers.getMapper(IUserSystemMapper.class);

    @Mapping(source = "id", target = "userSystemId")
    @Mapping(source = "email", target = "userSystemEmail")
    @Mapping(source = "username", target = "userSystemUsername")
    @Mapping(source = "password", target = "userSystemPassword")
    @Mapping(target = "userRolesEntity", ignore = true)
    @Mapping(target = "peopleEntity", ignore = true)
    UserSystemEntity dtoToEntity(UserSystemDto dto);

    @AfterMapping
    default void setPeopleEntity(@MappingTarget UserSystemEntity entity, UserSystemDto dto) {
        if (dto.getPeopleDto() != null) {
            entity.setPeopleEntity(IPeopleMapper.INSTANCE.dtoToEntity(dto.getPeopleDto()));
        }
    }

    @AfterMapping
    default void setRolesEntity(@MappingTarget UserSystemEntity entity, UserSystemDto dto) {
        if (dto.getUserRolesDto() != null) {
            dto.getUserRolesDto().forEach(
                    userRoleDto -> entity.getUserRolesEntity().add(IUserRoleMapper.INSTANCE.dtosToEntity(userRoleDto))
            );

        }
    }

    @InheritInverseConfiguration(name = "dtoToEntity")
    UserSystemDto entityToDto(UserSystemEntity entity);


    @AfterMapping
    default void setPeopleDto(@MappingTarget UserSystemDto dto, UserSystemEntity entity) {
        if (entity.getPeopleEntity() != null) {
            dto.setPeopleDto(IPeopleMapper.INSTANCE.entityToDto(entity.getPeopleEntity()));
        }
    }

    @AfterMapping
    default void setRolesDto(@MappingTarget UserSystemDto dto, UserSystemEntity entity) {
        if (entity.getUserRolesEntity() != null) {
            entity.getUserRolesEntity().forEach(
                    userRoleEntity -> dto.getUserRolesDto().add(IUserRoleMapper.INSTANCE.entityToDtos(userRoleEntity)));
        }
    }


    @InheritInverseConfiguration(name = "entityToDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserSystemEntity updateEntityFromDto(UserSystemDto dto, @MappingTarget UserSystemEntity entity);
}
