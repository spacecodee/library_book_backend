package com.spacecodee.library_book_backend.controller.category.book;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.core.controller.ICRUDController;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookADto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookLDto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookUDto;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.service.category.book.CategoryBookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/category-book")
public class CategoryBookController implements ICRUDController<CategoryBookADto, CategoryBookLDto, CategoryBookUDto,
        Integer> {

    private final CategoryBookServiceImpl categoryBookService;
    private final MessageUtilComponent messageUtilComponent;

    public CategoryBookController(CategoryBookServiceImpl categoryBookService,
                                  MessageUtilComponent messageUtilComponent) {
        this.categoryBookService = categoryBookService;
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

        this.categoryBookService.add(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.category.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int id, CategoryBookUDto dto) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        dto.setId(id);
        this.categoryBookService.update(lang, dto);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("update.success.category.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, Integer id) {
        HttpResponseApi httpResponseApiMsg = new HttpResponseApi();

        this.categoryBookService.delete(lang, id);
        httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.category.book", lang));
        httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(httpResponseApiMsg, HttpStatus.OK);
    }
}
