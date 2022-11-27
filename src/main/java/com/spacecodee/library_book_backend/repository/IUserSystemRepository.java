package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.user.system.UserSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSystemRepository extends JpaRepository<UserSystemEntity, Integer> {

    Optional<UserSystemEntity> findByUserSystemUsername(String name);

    boolean existsByUserSystemUsername(String name);

    boolean existsByPeopleEntityPeoplePhone(int peoplePhone);

    boolean existsByUserSystemEmail(String userEmail);

    Optional<UserSystemEntity> getByUserSystemUsername(String userSystemUsername);
}