package com.spacecodee.library_book_backend.service.rating.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.mappers.rating.book.IRatingBookKeyMapper;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookKeyVo;
import com.spacecodee.library_book_backend.model.vo.rating.book.RatingBookVo;
import com.spacecodee.library_book_backend.service.user.client.UserClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RatingBookServiceImpl {

    private final RatingBookService ratingBookService;
    private final UserClientService userClientService;
    private final ExceptionShortComponent exceptionShortComponent;

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingBookServiceImpl.class);
    private static final String GET_BY_ID_ERROR_RATING_BOOK = "get.by.id.error.rating.book";

    public RatingBookServiceImpl(RatingBookService ratingBookService,
                                 UserClientService userClientService, ExceptionShortComponent exceptionShortComponent) {
        this.ratingBookService = ratingBookService;
        this.userClientService = userClientService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    private void noExistRating(String lang, RatingBookKeyVo dto) {
        if (!this.ratingBookService.existRating(dto)) {
            throw this.exceptionShortComponent.existFound(GET_BY_ID_ERROR_RATING_BOOK, lang);
        }
    }

    public void add(String lang, int bookId, double ratingBook, String username) {
        var clientId = this.userClientService.getUserClientIdByUsername(username);
        final var dto = new RatingBookVo(clientId, bookId, ratingBook);
        try {
            this.ratingBookService.add(dto);
        } catch (NotAddSqlException e) {
            e.printStackTrace(System.err);
            RatingBookServiceImpl.LOGGER.error("error adding: {}", e.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.rating.book", lang);
        }
    }

    public void update(String lang, RatingBookVo dto) {
        try {
            this.ratingBookService.update(dto);
        } catch (NotUpdateSqlException e) {
            e.printStackTrace(System.err);
            RatingBookServiceImpl.LOGGER.error("error adding: {}", e.getMessage());
            throw this.exceptionShortComponent.noUpdateSql("update.error.rating.book", lang);
        }
    }

    public void delete(String lang, int clientId, int bookId) {
        var rating = IRatingBookKeyMapper.INSTANCE.toDto(clientId, bookId);
        this.noExistRating(lang, rating);
        try {
            this.ratingBookService.delete(rating);
        } catch (NotDeleteSqlException exception) {
            exception.printStackTrace(System.err);
            RatingBookServiceImpl.LOGGER.error("error deleting: {}", exception.getMessage());
            throw this.exceptionShortComponent.notAddSql("delete.error.rating.book", lang);
        }
    }
}
