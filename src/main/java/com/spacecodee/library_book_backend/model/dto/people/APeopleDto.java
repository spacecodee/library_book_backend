package com.spacecodee.library_book_backend.model.dto.people;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.PeopleEntity} entity
 */
@Getter
@Setter
public abstract class APeopleDto implements Serializable {

    @NotEmpty(message = "{not.empty.people.name}")
    private String name;
    @NotEmpty(message = "{not.empty.people.surname}")
    private String surname;
    @NotEmpty(message = "{not.empty.people.surname}")
    private int phone;
    private String address;
}