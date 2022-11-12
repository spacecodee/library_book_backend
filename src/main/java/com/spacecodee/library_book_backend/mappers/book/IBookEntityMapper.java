package com.spacecodee.library_book_backend.mappers.book;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBookEntityMapper {

    IBookEntityMapper INSTANCE = Mappers.getMapper(IBookEntityMapper.class);
}
