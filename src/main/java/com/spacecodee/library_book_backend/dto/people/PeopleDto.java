package com.spacecodee.library_book_backend.dto.people;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.PeopleEntity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PeopleDto implements Serializable {

    private int id;

    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The surname is required")
    private String surname;
    @NotEmpty(message = "The phone is required")
    private int phone;
    @NotEmpty(message = "The address is required")
    private String address;
}