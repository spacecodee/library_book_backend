package com.spacecodee.library_book_backend.model.dto.http;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class HttpResponseApi implements Serializable {

    private String message;
    @Setter(AccessLevel.PRIVATE)
    private String status;
    @Setter(AccessLevel.PRIVATE)
    private int statusCode;

    public void setHttpStatus(HttpStatus httpStatus) {
        this.statusCode = httpStatus.value();
        this.status = httpStatus.name();
    }
}
