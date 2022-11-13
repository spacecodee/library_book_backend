package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeopleRepository extends JpaRepository<PeopleEntity, Integer> {

    boolean existsByPeoplePhone(int phone);
}