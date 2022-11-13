package com.spacecodee.library_book_backend.mappers.user.client;

import com.spacecodee.library_book_backend.entity.PeopleEntity;
import com.spacecodee.library_book_backend.entity.UserClientEntity;
import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.mappers.people.IPeopleMapper;
import com.spacecodee.library_book_backend.model.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.model.dto.user.client.PUserClientDto;
import com.spacecodee.library_book_backend.model.vo.user.client.UserClientVo;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserClientMapper {

    IUserClientMapper INSTANCE = Mappers.getMapper(IUserClientMapper.class);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "email", source = "userEmail")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "userPassword")
    @Mapping(target = "peopleDto", source = "peopleEntity", qualifiedByName = "peopleDto")
    UserClientVo toVo(UserClientEntity entity);

    @Named("peopleDto")
    default PeopleDto mapPeopleDto(@NotNull PeopleEntity peopleEntity) {
        return IPeopleMapper.INSTANCE.toDto(peopleEntity);
    }

    @InheritInverseConfiguration(name = "toVo")
    @Mapping(target = "peopleEntity", source = "peopleDto", qualifiedByName = "peopleEntity")
    @Mapping(target = "userRolEntity", ignore = true)
    UserClientEntity toEntity(UserClientVo vo);

    @Named("peopleEntity")
    default PeopleEntity setPeopleEntity(@NotNull PeopleDto dto) {
        return IPeopleMapper.INSTANCE.toEntity(dto);
    }

    default void updateClientRoles(@NotNull UserClientEntity userClient, UserRoleEntity role) {
        userClient.setUserRolEntity(role);
    }

    default UserClientEntity mapId(int id) {
        return new UserClientEntity(id);
    }

    default List<String> getUserClientRoles(@NotNull PUserClientDto dto) {
        return dto.getAuthorities()
                  .stream()
                  .map(GrantedAuthority::getAuthority)
                  .collect(Collectors.toList());
    }
}
