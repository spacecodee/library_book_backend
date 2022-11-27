package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.user.client.UserClientEntity;
import com.spacecodee.library_book_backend.entity.user.client.projections.UserClientEntityAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserClientRepository extends JpaRepository<UserClientEntity, Integer> {

    Optional<UserClientEntity> findByUsername(String name);

    boolean existsByUsername(String name);

    boolean existsByPeopleEntityPeoplePhone(int peoplePhone);

    boolean existsByUserEmail(String userEmail);

    @Query("select u.userId from UserClientEntity u where u.username = ?1")
    Optional<Integer> getUserClientIdByUsername(String username);

    Optional<UserClientEntityAccount> getByUsername(String username);
}