package com.spacecodee.library_book_backend.service.rating.book;

import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookKeyVo;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookVo;

import java.util.Optional;

public interface IRatingBookService {


    Optional<Float> getPromedioByBookId(int id);

    Optional<Float> getRatingById(RatingBookKeyVo dto);

    boolean existRating(RatingBookKeyVo dto);

    void add(RatingBookVo dto);

    void update(RatingBookVo dto);

    void delete(RatingBookKeyVo dto);
}
