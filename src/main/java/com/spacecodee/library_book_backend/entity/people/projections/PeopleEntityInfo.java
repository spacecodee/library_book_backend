package com.spacecodee.library_book_backend.entity.people.projections;

/**
 * A Projection for the {@link com.spacecodee.library_book_backend.entity.people.PeopleEntity} entity
 */
public interface PeopleEntityInfo {
    String getPeopleName();

    String getPeopleSurname();

    String getPeopleAddress();
}