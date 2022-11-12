package com.spacecodee.library_book_backend.controller.category.book;

import com.spacecodee.library_book_backend.component.MessageUtilComponent;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.dto.http.HttpResponseApiMsg;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookAndBookDto;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookDto;
import com.spacecodee.library_book_backend.model.vo.category.book.CategoryBookVo;
import com.spacecodee.library_book_backend.service.category.book.CategoryBookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/category-book")
public class CategoryBookController implements ICategoryBookController {

    private final CategoryBookServiceImpl categoryBookService;
    private final MessageUtilComponent messageUtilComponent;

    private final HttpResponseApiMsg<List<CategoryBookDto>> categoriesResponse;
    private final HttpResponseApiMsg<CategoryBookDto> categoryResponse;
    private final HttpResponseApiMsg<List<CategoryBookAndBookDto>> categoriesAndBookResponse;
    private final HttpResponseApiMsg<CategoryBookAndBookDto> categoryAndBookResponse;
    private final HttpResponseApi httpResponseApiMsg;

    public CategoryBookController(CategoryBookServiceImpl categoryBookService,
                                  MessageUtilComponent messageUtilComponent) {
        this.categoryBookService = categoryBookService;
        this.messageUtilComponent = messageUtilComponent;
        this.categoriesResponse = new HttpResponseApiMsg<>();
        this.categoryResponse = new HttpResponseApiMsg<>();
        this.categoriesAndBookResponse = new HttpResponseApiMsg<>();
        this.categoryAndBookResponse = new HttpResponseApiMsg<>();
        this.httpResponseApiMsg = new HttpResponseApi();
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<CategoryBookDto>>> getAll(String lang) {
        this.categoriesResponse.setData(this.categoryBookService.getAll());
        this.categoriesResponse.setMessage(
                this.messageUtilComponent.getMessage("get.all.success.category.book", lang));
        this.categoriesResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(categoriesResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<CategoryBookDto>> getByCategoryId(String lang, int id) {
        this.categoryResponse.setData(this.categoryBookService.getById(lang, id));
        this.categoryResponse.setMessage(
                this.messageUtilComponent.getMessage("get.by.id.success.category.book", lang));
        this.categoryResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<List<CategoryBookAndBookDto>>> findAll(String lang) {
        this.categoriesAndBookResponse.setData(this.categoryBookService.findAllBy());

        if (this.categoriesAndBookResponse.getData().isEmpty()) {
            this.categoriesAndBookResponse.setMessage(
                    this.messageUtilComponent.getMessage("get.all.no.content.category.book", lang));
            this.categoriesAndBookResponse.setHttpStatus(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(this.categoriesAndBookResponse, HttpStatus.OK);
        }

        this.categoriesAndBookResponse.setMessage(
                this.messageUtilComponent.getMessage("get.all.success.category.book", lang));
        this.categoriesAndBookResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(this.categoriesAndBookResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApiMsg<CategoryBookAndBookDto>> getByIdCategoryBook(String lang, int categoryId) {
        this.categoryAndBookResponse.setData(this.categoryBookService.getByIdCategoryBook(lang, categoryId));
        this.categoryAndBookResponse.setMessage(
                this.messageUtilComponent.getMessage("get.by.id.success.category.book", lang));
        this.categoryAndBookResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(this.categoryAndBookResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> add(String lang, CategoryBookVo dto) {
        this.categoryBookService.add(lang, dto);
        this.httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("add.success.category.book", lang));
        this.httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(this.httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> update(String lang, int id, CategoryBookVo dto) {
        dto.setId(id);
        this.categoryBookService.update(lang, dto);
        this.httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("update.success.category.book", lang));
        this.httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(this.httpResponseApiMsg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseApi> delete(String lang, int id) {
        this.categoryBookService.delete(lang, id);
        this.httpResponseApiMsg.setMessage(this.messageUtilComponent.getMessage("delete.success.category.book", lang));
        this.httpResponseApiMsg.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(this.httpResponseApiMsg, HttpStatus.OK);
    }
}
