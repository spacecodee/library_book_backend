package com.spacecodee.library_book_backend.service.rating.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.dto.book.BookUDto;
import com.spacecodee.library_book_backend.dto.rating.book.RatingBookDto;
import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookDto;
import com.spacecodee.library_book_backend.dto.rating.book.UserRatingBookKeyDto;
import com.spacecodee.library_book_backend.dto.user.client.UserClientDto;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.mappers.rating.book.IUserRatingBookMapper;
import com.spacecodee.library_book_backend.service.book.BookServiceImpl;
import com.spacecodee.library_book_backend.service.user.client.UserClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RatingBookServiceImpl {

    private final RatingBookService ratingBookService;
    private final BookServiceImpl bookService;
    private final UserClientServiceImpl userClientService;

    private final ExceptionShortComponent exceptionShortComponent;

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingBookServiceImpl.class);

    public RatingBookServiceImpl(RatingBookService ratingBookService, BookServiceImpl bookService,
                                 UserClientServiceImpl userClientService,
                                 ExceptionShortComponent exceptionShortComponent) {
        this.ratingBookService = ratingBookService;
        this.bookService = bookService;
        this.userClientService = userClientService;
        this.exceptionShortComponent = exceptionShortComponent;
    }

    public UserRatingBookDto getById(String lang, UserRatingBookKeyDto dto) {
        return this.ratingBookService
                .getById(dto)
                .orElseThrow(() -> this.exceptionShortComponent.notFound("get.by.id.error.rating.book", lang));
    }

    private void existRating(String lang, UserRatingBookKeyDto dto) {
        if (this.ratingBookService.existRating(dto)) {
            throw this.exceptionShortComponent.existFound("get.by.id.exist.rating.book", lang);
        }
    }

    public void add(String lang, RatingBookDto dto) {
        this.existRating(lang, new UserRatingBookKeyDto(dto.getClientId(), dto.getBookId()));

        final UserRatingBookDto userRatingBookDto = IUserRatingBookMapper.INSTANCE.pDtoToDto(dto);

        BookUDto book = this.bookService.getById(lang, dto.getBookId());
        UserClientDto userClient = this.userClientService.getById(lang, dto.getClientId());

        userRatingBookDto.setBookDto(book);
        userRatingBookDto.setUserClientDto(userClient);

        try {
            this.ratingBookService.add(userRatingBookDto);
        } catch (NotAddSqlException e) {
            e.printStackTrace(System.err);
            RatingBookServiceImpl.LOGGER.error("error adding: {}", e.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.rating.book", lang);
        }
    }
}
