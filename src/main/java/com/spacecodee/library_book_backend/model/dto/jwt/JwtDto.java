package com.spacecodee.library_book_backend.model.dto.jwt;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class JwtDto implements Serializable {

    private String token;
}
