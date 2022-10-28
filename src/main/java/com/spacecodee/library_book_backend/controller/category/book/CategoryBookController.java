package com.spacecodee.library_book_backend.controller.category.book;

import com.spacecodee.library_book_backend.component.ExceptionShortComponent;
import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.controller.IAllController;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookADto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookLDto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookUDto;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.exceptions.NotAddSqlException;
import com.spacecodee.library_book_backend.exceptions.NotDeleteSqlException;
import com.spacecodee.library_book_backend.exceptions.NotUpdateSqlException;
import com.spacecodee.library_book_backend.service.category.book.CategoryBookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/category-book")
public class CategoryBookController implements IAllController<CategoryBookLDto, CategoryBookADto, CategoryBookUDto> {

    private final CategoryBookServiceImpl categoryBookService;
    private final ExceptionShortComponent exceptionShortComponent;
    private final MessageUtilComponent messageUtilComponent;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryBookController.class);

    public CategoryBookController(CategoryBookServiceImpl categoryBookService,
                                  ExceptionShortComponent exceptionShortComponent,
                                  MessageUtilComponent messageUtilComponent) {
        this.categoryBookService = categoryBookService;
        this.exceptionShortComponent = exceptionShortComponent;
        this.messageUtilComponent = messageUtilComponent;
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<CategoryBookLDto>>> list(String lang) {
        final HttpResponseApiMsg<List<CategoryBookLDto>> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.categoryBookService.getAll());

        if (httpResponseApiMsg.getData().isEmpty()) {
            httpResponseApiMsg.setMessage(
                    this.messageUtilComponent.getMessage("get.all.no.content.category.book", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
        }

        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.all.success.category.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<CategoryBookLDto>> getById(String lang, int id) {
        final HttpResponseApiMsg<CategoryBookLDto> httpResponseApiMsg = new HttpResponseApiMsg<>();
        httpResponseApiMsg.setData(this.categoryBookService.getById(lang, id));
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("get.by.id.success.category.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, CategoryBookADto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        try {
            this.categoryBookService.add(dto);
            httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.category.book", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        } catch (NotAddSqlException exception) {
            CategoryBookController.LOGGER.error("error registering: {}", exception.getMessage());
            throw this.exceptionShortComponent.notAddSql("add.error.category.book", lang);
        }

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int id, CategoryBookUDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        dto.setId(id);
        try {
            this.categoryBookService.update(dto);
            httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("update.success.category.book", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        } catch (NotUpdateSqlException exception) {
            CategoryBookController.LOGGER.error("error updating: {}", exception.getMessage());
            throw this.exceptionShortComponent.noUpdateSql("update.error.category.book", lang);
        }

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int id) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        try {
            this.categoryBookService.delete(lang, id);
            httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.category.book", lang));
            httpResponseApiMsg.setHttpStatus(HttpStatus.OK);
        } catch (NotDeleteSqlException exception) {
            CategoryBookController.LOGGER.error("error deleting: {}", exception.getMessage());
            throw this.exceptionShortComponent.notAddSql("delete.error.category.book", lang);
        }

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
