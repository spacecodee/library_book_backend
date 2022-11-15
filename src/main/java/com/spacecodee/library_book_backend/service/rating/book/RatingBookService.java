package com.spacecodee.library_book_backend.service.rating.book;

import com.spacecodee.library_book_backend.mappers.rating.book.IRatingBookKeyMapper;
import com.spacecodee.library_book_backend.mappers.rating.book.IRatingBookMapper;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookKeyVo;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookVo;
import com.spacecodee.library_book_backend.repository.IRatingBookRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingBookService implements IRatingBookService {

    private final IRatingBookRepository iRatingBookRepository;

    public RatingBookService(IRatingBookRepository iRatingBookRepository) {
        this.iRatingBookRepository = iRatingBookRepository;
    }

    @Override
    public Optional<Float> getPromedioByBookId(int id) {
        return this.iRatingBookRepository.getPromedioRatingBook(id).or(() -> Optional.of(0f));
    }

    @Override
    public Optional<Float> getRatingById(@NotNull RatingBookKeyVo dto) {
        return this.iRatingBookRepository.findRatingBook(dto.getBookId(), dto.getClientId()).or(() -> Optional.of(0f));
    }

    @Override
    public boolean existRating(RatingBookKeyVo dto) {
        return this.iRatingBookRepository.existsById(IRatingBookKeyMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void add(RatingBookVo dto) {
        this.iRatingBookRepository.save(IRatingBookMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void update(RatingBookVo dto) {
        this.iRatingBookRepository.save(IRatingBookMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void delete(RatingBookKeyVo dto) {
        this.iRatingBookRepository.deleteById(IRatingBookKeyMapper.INSTANCE.toEntity(dto));
    }
}
