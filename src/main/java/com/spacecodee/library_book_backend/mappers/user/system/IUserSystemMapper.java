package com.spacecodee.library_book_backend.mappers.user.system;

import com.spacecodee.library_book_backend.dto.user.system.PUserSystemDto;
import com.spacecodee.library_book_backend.dto.user.system.UserSystemDto;
import com.spacecodee.library_book_backend.entity.UserSystemEntity;
import com.spacecodee.library_book_backend.mappers.people.IPeopleMapper;
import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

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
            List<SimpleGrantedAuthority> authorities = entity.getUserRolesEntity()
                                                             .stream()
                                                             .map(rol -> new SimpleGrantedAuthority(
                                                                     rol.getUserRoleName().name()
                                                             )).collect(Collectors.toList());
            dto.setAuthorities(authorities);
        }
    }

    default List<String> getUserSystemRoles(PUserSystemDto dto) {
        return dto.getAuthorities()
                  .stream()
                  .map(GrantedAuthority::getAuthority)
                  .collect(Collectors.toList());
    }
}
