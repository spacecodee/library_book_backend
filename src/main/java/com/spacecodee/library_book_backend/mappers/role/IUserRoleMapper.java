package com.spacecodee.library_book_backend.mappers.role;

import com.spacecodee.library_book_backend.dto.role.UserRoleDto;
import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.enums.RolNameEnum;
import com.spacecodee.library_book_backend.utils.Utils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserRoleMapper {

    IUserRoleMapper INSTANCE = Mappers.getMapper(IUserRoleMapper.class);

    @Mapping(source = "id", target = "userRoleId")
    @Mapping(source = "name", target = "userRoleName", qualifiedByName = "setRoleName")
    UserRoleEntity dtoToEntity(UserRoleDto dto);

    @Mapping(source = "id", target = "userRoleId")
    @Mapping(source = "name", target = "userRoleName", qualifiedByName = "setRolesName")
    UserRoleEntity dtosToEntity(UserRoleDto dto);

    @Named("setRoleName")
    default RolNameEnum setRoleName(String name) {
        return Utils.getROL_NAME_ENUMS()[2];
    }

    @Named("setRolesName")
    default RolNameEnum setRolesName(String name) {
        for (int i = 0; i < Utils.getROLES_STRING().length; i++) {
            if (name.contains(Utils.getROLES_STRING()[i])) {
                return Utils.getROL_NAME_ENUMS()[i];
            }
        }

        return Utils.getROL_NAME_ENUMS()[1];
    }

    @InheritInverseConfiguration(name = "dtoToEntity")
    @Mapping(source = "userRoleName", target = "name", qualifiedByName = "setRoleName")
    UserRoleDto entityToDto(UserRoleEntity entity);

    @InheritInverseConfiguration(name = "dtoToEntity")
    @Mapping(source = "userRoleName", target = "name", qualifiedByName = "setRolesName")
    UserRoleDto entityToDtos(UserRoleEntity entity);

    @Named("setRoleName")
    default String setRoleName(RolNameEnum userRoleName) {
        return Utils.getROLES_STRING()[2];
    }

    @Named("setRolesName")
    default String setRolesName(RolNameEnum userRoleName) {
        for (int i = 0; i < Utils.getROL_NAME_ENUMS().length; i++) {
            if (userRoleName.equals(Utils.getROL_NAME_ENUMS()[i])) {
                return Utils.getROLES_STRING()[i];
            }
        }

        return Utils.getROLES_STRING()[1];
    }

    @InheritInverseConfiguration(name = "entityToDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserRoleEntity updateEntityFromDto(UserRoleDto dto, @MappingTarget UserRoleEntity entity);

    @InheritInverseConfiguration(name = "updateEntityFromDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserRoleDto updateDtoFromEntity(UserRoleEntity entity, @MappingTarget UserRoleDto dto);

    default RolNameEnum getRole(String name) {
        for (int i = 0; i < Utils.getROLES_STRING().length; i++) {
            if (name.contains(Utils.getROLES_STRING()[i])) {
                return Utils.getROL_NAME_ENUMS()[i];
            }
        }

        return Utils.getROL_NAME_ENUMS()[2];
    }
}
