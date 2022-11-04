package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import com.spacecodee.library_book_backend.enums.RolNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {

    Optional<UserRoleEntity> findByUserRoleName(RolNameEnum name);
}