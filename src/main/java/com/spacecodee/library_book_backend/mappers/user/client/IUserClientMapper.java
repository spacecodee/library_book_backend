package com.spacecodee.library_book_backend.mappers.user.client;

import com.spacecodee.library_book_backend.dto.user.client.PUserClientDto;
import com.spacecodee.library_book_backend.dto.user.client.UserClientADto;
import com.spacecodee.library_book_backend.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.dto.user.client.UserClientUDto;
import com.spacecodee.library_book_backend.entity.UserClientEntity;
import com.spacecodee.library_book_backend.mappers.people.IPeopleMapper;
import com.spacecodee.library_book_backend.mappers.role.IUserRoleMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    //client a
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "peopleDto", target = "peopleDto")
    @Mapping(target = "userRolDto", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserClientDto aDtoToDto(UserClientADto dto);

    //client u
    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "peopleDto", target = "peopleDto")
    @Mapping(target = "userRolDto", ignore = true)
    UserClientDto uDtoToDto(UserClientUDto dto);

    //principal user
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "userPassword")
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "email", source = "userEmail")
    PUserClientDto entityToPDto(UserClientEntity entity);

    @AfterMapping
    default void setFullName(@MappingTarget PUserClientDto dto, UserClientEntity entity) {
        if (entity.getPeopleEntity() != null) {
            dto.setFullName(
                    entity.getPeopleEntity().getPeopleName() + " " + entity.getPeopleEntity().getPeopleSurname());
        }
    }

    @AfterMapping
    default void setAuthorities(@MappingTarget PUserClientDto dto, UserClientEntity entity) {
        if (entity.getUserRolEntity() != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(entity.getUserRolEntity().getUserRoleName().name()));
            dto.setAuthorities(authorities);
        }
    }

    default List<String> getUserClientRoles(PUserClientDto dto) {
        if (!dto.getAuthorities().isEmpty()) {
            return dto.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        }

        return List.of();
    }
}
