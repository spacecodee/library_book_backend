package com.spacecodee.library_book_backend.mappers.user.client;

import com.spacecodee.library_book_backend.entity.PeopleEntity;
import com.spacecodee.library_book_backend.entity.UserClientEntity;
import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.mappers.people.IPeopleMapper;
import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import com.spacecodee.library_book_backend.model.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.model.dto.role.RoleDto;
import com.spacecodee.library_book_backend.model.dto.user.client.PUserClientDto;
import com.spacecodee.library_book_backend.model.dto.user.client.UserClientDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserClientReadMapper {

    IUserClientReadMapper INSTANCE = Mappers.getMapper(IUserClientReadMapper.class);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "userEmail")
    @Mapping(target = "password", source = "userPassword")
    @Mapping(target = "peopleDto", source = "peopleEntity", qualifiedByName = "peopleDto")
    @Mapping(target = "roleDto", source = "userRolEntity", qualifiedByName = "roleDto")
    UserClientDto toDto(UserClientEntity entity);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "peopleEntity", source = "peopleDto", qualifiedByName = "peopleEntity")
    @Mapping(target = "userRolEntity", source = "roleDto", qualifiedByName = "userRolEntity")
    UserClientEntity toEntity(UserClientDto dto);

    @Named("peopleDto")
    default PeopleDto setPeopleDto(@NotNull PeopleEntity entity) {
        return IPeopleMapper.INSTANCE.toDto(entity);
    }

    @Named("peopleEntity")
    default PeopleEntity setPeopleEntity(@NotNull PeopleDto dto) {
        return IPeopleMapper.INSTANCE.toEntity(dto);
    }

    @Named("roleDto")
    default RoleDto setRoleDto(@NotNull UserRoleEntity entity) {
        return IUserRoleMapper.INSTANCE.entityToDto(entity);
    }

    @Named("userRolEntity")
    default UserRoleEntity setRole(@NotNull RoleDto dto) {
        return IUserRoleMapper.INSTANCE.dtoToEntity(dto);
    }

    default List<UserClientDto> mapClients(@NotNull List<UserClientEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Mapping(target = "email", source = "userEmail")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "userPassword")
    @Mapping(target = "fullName", source = "peopleEntity", qualifiedByName = "fullName")
    @Mapping(target = "authorities", source = "userRolEntity", qualifiedByName = "authorities")
    PUserClientDto entityToPDto(UserClientEntity entity);

    @Named("fullName")
    default String setFullName(@NotNull PeopleEntity people) {
        return people.getPeopleName() + " " + people.getPeopleSurname();
    }

    @Named("authorities")
    default Collection<GrantedAuthority> setAuthorities(UserRoleEntity role) {
        return IUserRoleMapper.INSTANCE.mapAuthority(role);
    }
}
