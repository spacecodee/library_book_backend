package com.spacecodee.library_book_backend.entity;

import com.spacecodee.library_book_backend.enums.RolNameEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_role", schema = "public", catalog = "spacecodee")
public class UserRoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_role_id", nullable = false)
    private int userRoleId;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role_name", nullable = false, length = 100)
    private RolNameEnum userRoleName;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return userRoleId == that.userRoleId && Objects.equals(userRoleName, that.userRoleName);
    }

    @Override public int hashCode() {
        return Objects.hash(userRoleName, userRoleId);
    }
}
