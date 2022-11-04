package com.spacecodee.library_book_backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_system", schema = "public", catalog = "spacecodee")
public class UserSystemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_system_id", nullable = false)
    private int userSystemId;
    @Basic
    @Column(name = "user_system_email", nullable = false, length = 150)
    private String userSystemEmail;
    @Basic
    @Column(name = "user_system_username", nullable = false, length = 50)
    private String userSystemUsername;
    @Basic
    @Column(name = "user_system_password", nullable = false)
    private String userSystemPassword;
    @OneToOne(targetEntity = PeopleEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "people_id", referencedColumnName = "people_id", nullable = false)
    private PeopleEntity peopleEntity;
    @ToString.Exclude
    @ManyToMany(targetEntity = UserRoleEntity.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "user_system_middle_role", joinColumns = @JoinColumn(name = "user_system_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private Set<UserRoleEntity> userRolesEntity = new HashSet<>();

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSystemEntity that = (UserSystemEntity) o;
        return userSystemId == that.userSystemId && Objects.equals(userSystemEmail, that.userSystemEmail)
                && Objects.equals(userSystemUsername, that.userSystemUsername)
                && Objects.equals(userSystemPassword, that.userSystemPassword);
    }

    @Override public int hashCode() {
        return Objects.hash(userSystemId, userSystemEmail, userSystemUsername, userSystemPassword);
    }
}
