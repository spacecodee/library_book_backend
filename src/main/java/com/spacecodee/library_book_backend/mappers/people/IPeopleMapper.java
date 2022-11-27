package com.spacecodee.library_book_backend.mappers.people;

import com.spacecodee.library_book_backend.entity.people.PeopleEntity;
import com.spacecodee.library_book_backend.model.dto.people.PeopleDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPeopleMapper {

    IPeopleMapper INSTANCE = Mappers.getMapper(IPeopleMapper.class);

    @Mapping(source = "id", target = "peopleId")
    @Mapping(source = "name", target = "peopleName")
    @Mapping(source = "surname", target = "peopleSurname")
    @Mapping(source = "phone", target = "peoplePhone")
    @Mapping(source = "address", target = "peopleAddress")
    PeopleEntity toEntity(PeopleDto dto);

    @InheritInverseConfiguration(name = "toEntity")
    PeopleDto toDto(PeopleEntity entity);
}
