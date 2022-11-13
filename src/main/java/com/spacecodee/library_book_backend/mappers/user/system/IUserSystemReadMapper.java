package com.spacecodee.library_book_backend.mappers.user.system;

import com.spacecodee.library_book_backend.entity.PeopleEntity;
import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.entity.UserSystemEntity;
import com.spacecodee.library_book_backend.mappers.people.IPeopleMapper;
import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import com.spacecodee.library_book_backend.model.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import com.spacecodee.library_book_backend.model.dto.user.system.PUserSystemDto;
import com.spacecodee.library_book_backend.model.dto.user.system.UserSystemDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserSystemReadMapper {

    IUserSystemReadMapper INSTANCE = Mappers.getMapper(IUserSystemReadMapper.class);

    default List<UserSystemDto> mapClients(@NotNull List<UserSystemEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Mapping(target = "userSystemId", source = "id")
    @Mapping(target = "userSystemEmail", source = "email")
    @Mapping(target = "userSystemUsername", source = "username")
    @Mapping(target = "userSystemPassword", source = "password")
    @Mapping(target = "userRolesEntity", source = "roleDto", qualifiedByName = "userRolesEntity")
    @Mapping(target = "peopleEntity", source = "peopleDto", qualifiedByName = "peopleEntity")
    UserSystemEntity toEntity(UserSystemDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "peopleDto", source = "peopleEntity", qualifiedByName = "peopleDto")
    @Mapping(target = "roleDto", source = "userRolesEntity", qualifiedByName = "roleDto")
    UserSystemDto toDto(UserSystemEntity entity);

    @Named("peopleDto")
    default PeopleDto mapPeopleDto(PeopleEntity people) {
        return IPeopleMapper.INSTANCE.toDto(people);
    }

    @Named("roleDto")
    default Set<RoleDto> mapDtoRoles(Set<UserRoleEntity> roles) {
        return IUserRoleMapper.INSTANCE.mapRoles(roles);
    }

    @Named("peopleEntity")
    default PeopleEntity mapPeopleEntity(PeopleDto dto) {
        return IPeopleMapper.INSTANCE.toEntity(dto);
    }

    @Named("userRolesEntity")
    default Set<UserRoleEntity> mapEntityRoles(Set<RoleDto> roles) {
        return IUserRoleMapper.INSTANCE.mapEntityRoles(roles);
    }

    @Mapping(target = "username", source = "userSystemUsername")
    @Mapping(target = "password", source = "userSystemPassword")
    @Mapping(target = "email", source = "userSystemEmail")
    @Mapping(target = "fullName", source = "peopleEntity", qualifiedByName = "fullName")
    @Mapping(target = "authorities", source = "userRolesEntity", qualifiedByName = "authorities")
    PUserSystemDto entityToPDto(UserSystemEntity entity);

    @Named("fullName")
    default String setFullName(@NotNull PeopleEntity people) {
        return people.getPeopleName() + " " + people.getPeopleSurname();
    }

    @Named("authorities")
    default Collection<GrantedAuthority> setAuthorities(Set<UserRoleEntity> role) {
        return IUserRoleMapper.INSTANCE.mapAuthorities(role);
    }

    default List<String> getUserSystemRoles(@NotNull PUserSystemDto dto) {
        return dto.getAuthorities()
                  .stream()
                  .map(GrantedAuthority::getAuthority)
                  .collect(Collectors.toList());
    }
}
