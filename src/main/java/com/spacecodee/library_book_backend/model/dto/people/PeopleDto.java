package com.spacecodee.library_book_backend.model.dto.people;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.spacecodee.library_book_backend.entity.PeopleEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PeopleDto extends APeopleDto implements Serializable {

    private int id;
}