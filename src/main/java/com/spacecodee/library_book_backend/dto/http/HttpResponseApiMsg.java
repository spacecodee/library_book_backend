package com.spacecodee.library_book_backend.dto.http;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HttpResponseApiMsg<D> {

    private String message;
    @Setter(AccessLevel.PRIVATE)
    private String status;
    @Setter(AccessLevel.PRIVATE)
    private int statusCode;
    @Setter(AccessLevel.PRIVATE)
    private LocalDate localDate = LocalDate.now();
    private D data;

    public void setHttpStatus(HttpStatus httpStatus) {
        this.statusCode = httpStatus.value();
        this.status = httpStatus.name();
    }
}
