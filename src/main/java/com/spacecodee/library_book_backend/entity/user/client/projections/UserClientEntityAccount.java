package com.spacecodee.library_book_backend.entity.user.client.projections;

import com.spacecodee.library_book_backend.entity.people.projections.PeopleEntityInfo;

/**
 * A Projection for the {@link com.spacecodee.library_book_backend.entity.user.client.UserClientEntity} entity
 */
public interface UserClientEntityAccount {
    String getUserEmail();

    String getUsername();

    PeopleEntityInfo getPeopleEntity();
}