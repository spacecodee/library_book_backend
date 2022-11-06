package com.spacecodee.library_book_backend.service.rating.book;

import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookDto;
import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookKeyDto;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.mappers.rating.book.IUserRatingBookKeyMapper;
import com.spacecodee.library_book_backend.mappers.rating.book.IUserRatingBookMapper;
import com.spacecodee.library_book_backend.repository.IUserRatingBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RatingBookService {

    private final IUserRatingBookRepository iUserRatingBookRepository;

    public RatingBookService(
            IUserRatingBookRepository iUserRatingBookRepository) {this.iUserRatingBookRepository = iUserRatingBookRepository;}

    public Optional<UserRatingBookDto> getById(UserRatingBookKeyDto dto) {
        return this.iUserRatingBookRepository.getByUserRatingBookId(IUserRatingBookKeyMapper.INSTANCE.toEntity(dto))
                                             .or(Optional::empty)
                                             .map(IUserRatingBookMapper.INSTANCE::entityToDto);
    }

    public boolean existRating(UserRatingBookKeyDto dto) {
        return this.iUserRatingBookRepository.existsByUserRatingBookId(IUserRatingBookKeyMapper.INSTANCE.toEntity(dto));
    }

    @Transactional(rollbackFor = NotAddSqlException.class)
    public void add(UserRatingBookDto dto) {
        this.iUserRatingBookRepository.save(IUserRatingBookMapper.INSTANCE.dtoToEntity(dto));
    }
}
