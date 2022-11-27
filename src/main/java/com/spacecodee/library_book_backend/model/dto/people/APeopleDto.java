package com.spacecodee.library_book_backend.model.dto.people;

import com.spacecodee.library_book_backend.entity.people.PeopleEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * A DTO for the {@link PeopleEntity} entity
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