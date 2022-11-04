package com.spacecodee.library_book_backend.mappers.people;

import com.spacecodee.library_book_backend.dto.people.PeopleDto;
import com.spacecodee.library_book_backend.entity.PeopleEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPeopleMapper {

    IPeopleMapper INSTANCE = Mappers.getMapper(IPeopleMapper.class);

    @Mapping(source = "id", target = "peopleId")
    @Mapping(source = "name", target = "peopleName")
    @Mapping(source = "surname", target = "peopleSurname")
    @Mapping(source = "phone", target = "peoplePhone")
    @Mapping(source = "address", target = "peopleAddress")
    PeopleEntity dtoToEntity(PeopleDto dto);

    @InheritInverseConfiguration(name = "dtoToEntity")
    PeopleDto entityToDto(PeopleEntity entity);

    @InheritInverseConfiguration(name = "entityToDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PeopleEntity updateEntityFromDto(PeopleDto dto, @MappingTarget PeopleEntity entity);
}
