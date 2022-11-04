package com.spacecodee.library_book_backend.mappers.role;

import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.enums.RolNameEnum;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserRoleMapper {

    String[] ROLES_STRING = {"user", "admin", "student"};
    RolNameEnum[] ROL_NAME_ENUMS = {RolNameEnum.ROLE_USER, RolNameEnum.ROLE_ADMIN, RolNameEnum.ROLE_STUDENT};
    IUserRoleMapper INSTANCE = Mappers.getMapper(IUserRoleMapper.class);

    @Mapping(source = "id", target = "userRoleId")
    @Mapping(target = "userRoleName", ignore = true)
    UserRoleEntity dtoToEntity(UserRoleDto dto);

    @AfterMapping
    default void setRoleName(@MappingTarget UserRoleEntity entity, UserRoleDto dto) {
        for (int i = 0; i < IUserRoleMapper.ROLES_STRING.length; i++) {
            if (dto.getName().contains(IUserRoleMapper.ROLES_STRING[i])) {
                entity.setUserRoleName(IUserRoleMapper.ROL_NAME_ENUMS[i]);
                return;
            }
            else {
                entity.setUserRoleName(IUserRoleMapper.ROL_NAME_ENUMS[2]);
            }
        }
    }

    @InheritInverseConfiguration(name = "dtoToEntity")
    UserRoleDto entityToDto(UserRoleEntity entity);

    @AfterMapping
    default void setRoleName(@MappingTarget UserRoleDto dto, UserRoleEntity entity) {
        for (int i = 0; i < IUserRoleMapper.ROL_NAME_ENUMS.length; i++) {
            if (entity.getUserRoleName().equals(IUserRoleMapper.ROL_NAME_ENUMS[i])) {
                dto.setName(IUserRoleMapper.ROLES_STRING[i]);
                return;
            }
            else {
                dto.setName(IUserRoleMapper.ROLES_STRING[2]);
            }
        }
    }

    @InheritInverseConfiguration(name = "entityToDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserRoleEntity updateEntityFromDto(UserRoleDto dto, @MappingTarget UserRoleEntity entity);

    default RolNameEnum getRole(String name) {
        for (int i = 0; i < IUserRoleMapper.ROLES_STRING.length; i++) {
            if (name.contains(IUserRoleMapper.ROLES_STRING[i])) {
                return IUserRoleMapper.ROL_NAME_ENUMS[i];
            }
        }

        return IUserRoleMapper.ROL_NAME_ENUMS[2];
    }
}
