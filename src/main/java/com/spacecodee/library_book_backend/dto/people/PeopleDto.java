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

    @NotEmpty(message = "{not.empty.people.name}")
    private String name;
    @NotEmpty(message = "{not.empty.people.surname}")
    private String surname;
    private int phone;
    private String address;
}