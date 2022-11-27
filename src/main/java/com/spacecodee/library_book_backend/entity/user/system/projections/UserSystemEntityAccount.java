package com.spacecodee.library_book_backend.entity.user.system.projections;

import com.spacecodee.library_book_backend.entity.people.projections.PeopleEntityInfo;

import java.util.Set;

/**
 * A Projection for the {@link com.spacecodee.library_book_backend.entity.user.system.UserSystemEntity} entity
 */
public interface UserSystemEntityAccount {
    String getUserSystemEmail();

    String getUserSystemUsername();

    Set<PeopleEntityInfo> getUserRolesEntity();
}