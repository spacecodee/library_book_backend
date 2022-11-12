package com.spacecodee.library_book_backend.mappers.rating.book;

import com.spacecodee.library_book_backend.entity.rating.projections.RatingBookEntityInfo;
import com.spacecodee.library_book_backend.model.dto.rating.book.RatingPromedioBookDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRatingBookReadMapper {

    IRatingBookReadMapper INSTANCE = Mappers.getMapper(IRatingBookReadMapper.class);

    default RatingPromedioBookDto getPromedio(@NotNull Set<RatingBookEntityInfo> rating) {
        var promedio = rating.stream().mapToDouble(RatingBookEntityInfo::getRatingBook).average().orElse(0);
        return new RatingPromedioBookDto(promedio);
    }

    default float getGlobalRating(@NotNull Set<RatingBookEntityInfo> rating) {
        return (float) rating.stream().mapToDouble(RatingBookEntityInfo::getRatingBook).average().orElse(0);
    }
}
