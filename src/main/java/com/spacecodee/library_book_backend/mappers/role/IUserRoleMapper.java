package com.spacecodee.library_book_backend.mappers.role;

import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.enums.RolNameEnum;
import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import com.spacecodee.library_book_backend.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserRoleMapper {

    IUserRoleMapper INSTANCE = Mappers.getMapper(IUserRoleMapper.class);

    @Mapping(target = "userRoleId", source = "id")
    @Mapping(target = "userRoleName", source = "name")
    UserRoleEntity toEntity(RoleDto dto);

    @Mapping(target = "id", source = "userRoleId")
    @Mapping(target = "name", source = "userRoleName")
    RoleDto toDto(UserRoleEntity roleDto);

    default Set<RoleDto> mapRoles(Set<UserRoleEntity> roles) {
        return roles.stream().map(this::toDto).collect(Collectors.toSet());
    }

    default Set<UserRoleEntity> mapEntityRoles(Set<RoleDto> roles) {
        return roles.stream().map(this::toEntity).collect(Collectors.toSet());
    }

    @Mapping(source = "id", target = "userRoleId")
    @Mapping(source = "name", target = "userRoleName", qualifiedByName = "setRoleName")
    UserRoleEntity dtoToEntity(RoleDto dto);

    @Mapping(source = "id", target = "userRoleId")
    @Mapping(source = "name", target = "userRoleName", qualifiedByName = "setRolesName")
    UserRoleEntity dtosToEntity(RoleDto dto);

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
    RoleDto entityToDto(UserRoleEntity entity);

    @InheritInverseConfiguration(name = "dtoToEntity")
    @Mapping(source = "userRoleName", target = "name", qualifiedByName = "setRolesName")
    RoleDto entityToDtos(UserRoleEntity entity);

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

    default RolNameEnum getRole(String name) {
        for (int i = 0; i < Utils.getROLES_STRING().length; i++) {
            if (name.contains(Utils.getROLES_STRING()[i])) {
                return Utils.getROL_NAME_ENUMS()[i];
            }
        }

        return Utils.getROL_NAME_ENUMS()[2];
    }

    default Collection<GrantedAuthority> mapAuthority(UserRoleEntity role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getUserRoleName().name()));
        return authorities;
    }

    default Collection<GrantedAuthority> mapAuthorities(@NotNull Set<UserRoleEntity> role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoleEntity userRoleEntity : role) {
            authorities.add(new SimpleGrantedAuthority(userRoleEntity.getUserRoleName().name()));
        }
        return authorities;
    }
}
