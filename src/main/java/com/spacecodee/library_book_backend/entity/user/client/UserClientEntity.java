package com.spacecodee.library_book_backend.entity.user.client;

import com.spacecodee.library_book_backend.entity.people.PeopleEntity;
import com.spacecodee.library_book_backend.entity.UserRoleEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_client", schema = "public", catalog = "spacecodee")
public class UserClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_client_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "user_client_email", nullable = false, length = 200)
    private String userEmail;
    @Basic
    @Column(name = "user_client_username", nullable = false, length = 100)
    private String username;
    @Basic
    @Column(name = "user_client_password", nullable = false, length = -1)
    private String userPassword;
    @OneToOne(targetEntity = UserRoleEntity.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id", nullable = false)
    private UserRoleEntity userRolEntity;
    @OneToOne(targetEntity = PeopleEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "people_id", referencedColumnName = "people_id", nullable = false)
    private PeopleEntity peopleEntity;

    public UserClientEntity(int userId) {
        this.userId = userId;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserClientEntity that = (UserClientEntity) o;
        return userId == that.userId && peopleEntity == that.peopleEntity
                && Objects.equals(userEmail, that.userEmail)
                && Objects.equals(username, that.username)
                && Objects.equals(userPassword, that.userPassword);
    }

    @Override public int hashCode() {
        return Objects.hash(userId, userEmail, username, userPassword, peopleEntity);
    }
}
