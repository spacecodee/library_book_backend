package com.spacecodee.library_book_backend.mappers.user.system;

import com.spacecodee.library_book_backend.entity.PeopleEntity;
import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.entity.UserSystemEntity;
import com.spacecodee.library_book_backend.mappers.people.IPeopleMapper;
import com.spacecodee.library_book_backend.model.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.model.vo.user.system.UserSystemVo;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserSystemMapper {

    IUserSystemMapper INSTANCE = Mappers.getMapper(IUserSystemMapper.class);

    @Mapping(target = "userSystemId", source = "id")
    @Mapping(target = "userSystemEmail", source = "email")
    @Mapping(target = "userSystemUsername", source = "username")
    @Mapping(target = "userSystemPassword", source = "password")
    @Mapping(target = "peopleEntity", source = "peopleDto", qualifiedByName = "peopleEntity")
    @Mapping(target = "userRolesEntity", ignore = true)
    UserSystemEntity toEntity(UserSystemVo entity);

    @Named("peopleEntity")
    default PeopleEntity mapPeopleEntity(@NotNull PeopleDto dto) {
        return IPeopleMapper.INSTANCE.toEntity(dto);
    }

    default void updateSystemRoles(UserSystemEntity userClient, Set<UserRoleEntity> roles) {
        userClient.setUserRolesEntity(roles);
    }
    /*
    @AfterMapping
    default void setPeopleEntity(@MappingTarget UserSystemEntity entity, UserSystemDto dto) {
        if (dto.getPeopleDto() != null) {
            entity.setPeopleEntity(IPeopleMapper.INSTANCE.toEntity(dto.getPeopleDto()));
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


    @AfterMapping
    default void setPeopleDto(@MappingTarget UserSystemDto dto, UserSystemEntity entity) {
        if (entity.getPeopleEntity() != null) {
            dto.setPeopleDto(IPeopleMapper.INSTANCE.toDto(entity.getPeopleEntity()));
        }
    }

    @AfterMapping
    default void setRolesDto(@MappingTarget UserSystemDto dto, UserSystemEntity entity) {
        if (entity.getUserRolesEntity() != null) {
            entity.getUserRolesEntity().forEach(
                    userRoleEntity -> dto.getUserRolesDto().add(IUserRoleMapper.INSTANCE.entityToDtos(userRoleEntity)));
        }
    }
    //principal user

    @Mapping(target = "username", source = "userSystemUsername")
    @Mapping(target = "password", source = "userSystemPassword")
    @Mapping(target = "email", source = "userSystemEmail")
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    PUserSystemDto entityToPDto(UserSystemEntity entity);

    @AfterMapping
    default void setFullName(@MappingTarget PUserSystemDto dto, UserSystemEntity entity) {
        if (entity.getPeopleEntity() != null) {
            dto.setFullName(
                    entity.getPeopleEntity().getPeopleName() + " " + entity.getPeopleEntity().getPeopleSurname());
        }
    }

    @AfterMapping
    default void setAuthorities(@MappingTarget PUserSystemDto dto, UserSystemEntity entity) {
        if (!entity.getUserRolesEntity().isEmpty()) {
            List<SimpleGrantedAuthority> authorities = entity
                    .getUserRolesEntity()
                    .stream()
                    .map(rol -> new SimpleGrantedAuthority(
                            rol.getUserRoleName().name()
                    )).collect(Collectors.toList());
            dto.setAuthorities(authorities);
        }
    }

    */
}
