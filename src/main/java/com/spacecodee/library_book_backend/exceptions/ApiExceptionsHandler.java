package com.spacecodee.library_book_backend.exceptions;

import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApi;
import com.spacecodee.library_book_backend.model.dto.http.HttpResponseApiMsg;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

    private final HttpResponseApiMsg<List<String>> httpResponseApiMsg = new HttpResponseApiMsg<>();
    private final HttpResponseApi httpResponseApi = new HttpResponseApi();

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponseApi> notFoundHandlerException(NotFoundException notFoundException) {
        this.httpResponseApi.setHttpStatus(HttpStatus.NOT_FOUND);
        this.httpResponseApi.setMessage(notFoundException.getMessage());
        return new ResponseEntity<>(this.httpResponseApi, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistFoundException.class)
    public ResponseEntity<HttpResponseApi> existFoundHandlerException(
            ExistFoundException existFoundException) {
        this.httpResponseApi.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.httpResponseApi.setMessage(existFoundException.getMessage());
        return new ResponseEntity<>(this.httpResponseApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAddSqlException.class)
    public ResponseEntity<HttpResponseApi> notAddSqlHandlerException(NotAddSqlException notAddSqlException) {
        this.httpResponseApi.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.httpResponseApi.setMessage(notAddSqlException.getMessage());
        return new ResponseEntity<>(this.httpResponseApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotUpdateSqlException.class)
    public ResponseEntity<HttpResponseApi> notUpdateSqlHandlerException(
            NotUpdateSqlException notUpdateException) {
        this.httpResponseApi.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.httpResponseApi.setMessage(notUpdateException.getMessage());
        return new ResponseEntity<>(this.httpResponseApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IsRequiredException.class)
    public ResponseEntity<HttpResponseApi> isRequiredHandlerException(
            IsRequiredException isRequiredException) {
        this.httpResponseApi.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.httpResponseApi.setMessage(isRequiredException.getMessage());
        return new ResponseEntity<>(this.httpResponseApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotDeleteSqlException.class)
    public ResponseEntity<HttpResponseApi> notDeleteHandlerException(
            NotDeleteSqlException notDeleteSqlException) {
        this.httpResponseApi.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.httpResponseApi.setMessage(notDeleteSqlException.getMessage());
        return new ResponseEntity<>(this.httpResponseApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginAuthException.class)
    public ResponseEntity<HttpResponseApi> loginAuthHandlerException(
            LoginAuthException exception) {
        this.httpResponseApi.setHttpStatus(HttpStatus.UNAUTHORIZED);
        this.httpResponseApi.setMessage(exception.getMessage());
        return new ResponseEntity<>(this.httpResponseApi, HttpStatus.UNAUTHORIZED);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        var erros = ex.getBindingResult()
                      .getFieldErrors()
                      .stream()
                      .map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.toList());
        this.httpResponseApiMsg.setMessage("Validation error");
        this.httpResponseApiMsg.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.httpResponseApiMsg.setData(erros);
        return new ResponseEntity<>(this.httpResponseApiMsg, HttpStatus.BAD_REQUEST);
    }
}
