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
}
