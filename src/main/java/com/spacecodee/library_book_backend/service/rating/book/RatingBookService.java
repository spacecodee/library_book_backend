package com.spacecodee.library_book_backend.service.rating.book;

import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookDto;
import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookKeyDto;
import com.spacecodee.library_book_backend.dto.rating.book.read.GetRatingByIdDto;
import com.spacecodee.library_book_backend.dto.rating.book.read.RatingBookRDto;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.mappers.rating.book.IRatingBookReadMapper;
import com.spacecodee.library_book_backend.mappers.rating.book.IUserRatingBookKeyMapper;
import com.spacecodee.library_book_backend.mappers.rating.book.IUserRatingBookMapper;
import com.spacecodee.library_book_backend.repository.IRatingBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingBookService {

    private final IRatingBookRepository iRatingBookRepository;

    public RatingBookService(
            IRatingBookRepository iRatingBookRepository) {this.iRatingBookRepository = iRatingBookRepository;}

    public List<RatingBookRDto> getAll() {
        final List<RatingBookRDto> booksLDto = new ArrayList<>();
        this.iRatingBookRepository.findAll().forEach(bookEntity -> booksLDto.add(
                IUserRatingBookMapper.INSTANCE.toRDto(bookEntity)));
        return booksLDto;
    }

    public Optional<GetRatingByIdDto> getById(int userId, int bookId) {
        return this.iRatingBookRepository
                .getByUserClientEntityUserIdAndBookEntityBookId(userId, bookId)
                .or(Optional::empty)
                .map(IRatingBookReadMapper.INSTANCE::toDto);
    }

    public Optional<Float> getPromedioByBookId(int id) {
        return this.iRatingBookRepository.getPromedioRatingBook(id).or(Optional::empty);
    }

    public float getRatingByBookId(int bookId, int clientId) {
        return this.iRatingBookRepository.findByRatingBook(bookId, clientId);
    }

    public boolean existRating(UserRatingBookKeyDto dto) {
        return this.iRatingBookRepository.existsByUserRatingBookId(IUserRatingBookKeyMapper.INSTANCE.toEntity(dto));
    }

    @Transactional(rollbackFor = NotAddSqlException.class)
    public void add(UserRatingBookDto dto) {
        this.iRatingBookRepository.save(IUserRatingBookMapper.INSTANCE.dtoToEntity(dto));
    }

    @Transactional(rollbackFor = NotUpdateSqlException.class)
    public void update(UserRatingBookDto dto) {
        this.iRatingBookRepository.save(IUserRatingBookMapper.INSTANCE.dtoToEntity(dto));
    }

    @Transactional(rollbackFor = NotDeleteSqlException.class)
    public void delete(UserRatingBookKeyDto dto) {
        this.iRatingBookRepository.deleteById(IUserRatingBookKeyMapper.INSTANCE.toEntity(dto));
    }
}
