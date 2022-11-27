package com.spacecodee.library_book_backend.mappers.user;

import com.spacecodee.library_book_backend.entity.user.client.projections.UserClientEntityAccount;
import com.spacecodee.library_book_backend.entity.user.system.projections.UserSystemEntityAccount;
import com.spacecodee.library_book_backend.model.pojo.UserAccountPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IGeneralUserMapper {

    IGeneralUserMapper INSTANCE = Mappers.getMapper(IGeneralUserMapper.class);


    @Mapping(target = "email", source = "userEmail")
    @Mapping(target = "username", source = "userEmail")
    @Mapping(target = "name", source = "peopleEntity.peopleName")
    @Mapping(target = "surname", source = "peopleEntity.peopleSurname")
    @Mapping(target = "address", source = "peopleEntity.peopleAddress")
    UserAccountPojo toClientPojo(UserClientEntityAccount entity);

    @Mapping(target = "email", source = "userSystemEmail")
    @Mapping(target = "username", source = "userSystemEmail")
    @Mapping(target = "name", source = "peopleEntity.peopleName")
    @Mapping(target = "surname", source = "peopleEntity.peopleSurname")
    @Mapping(target = "address", source = "peopleEntity.peopleAddress")
    UserAccountPojo toSystemPojo(UserSystemEntityAccount entity);
}
