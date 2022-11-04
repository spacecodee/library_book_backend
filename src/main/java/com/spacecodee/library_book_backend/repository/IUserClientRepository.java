package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.UserClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserClientRepository extends JpaRepository<UserClientEntity, Integer> {

    Optional<UserClientEntity> findByUsername(String name);

    boolean existsByUsername(String name);

    boolean existsByPeopleEntityPeoplePhone(int peoplePhone);

    boolean existsByUserEmail(String userEmail);
}