package com.spacecodee.library_book_backend.entity.rating;

import com.spacecodee.library_book_backend.entity.BookEntity;
import com.spacecodee.library_book_backend.entity.UserEntity;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_rating_book", schema = "public", catalog = "spacecodee")
public class UserRatingBookEntity {

    @EmbeddedId
    private UserRatingBookKeyEntity userRatingBookId;
    @Basic
    @Column(name = "rating_book", nullable = false)
    private short ratingBook;
    @MapsId("ratingUserId")
    @ManyToOne(targetEntity = UserEntity.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "rating_user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userEntity;
    @MapsId("ratingUserId")
    @ManyToOne(targetEntity = BookEntity.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "rating_book_id", referencedColumnName = "book_id", nullable = false)
    private BookEntity bookEntity;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRatingBookEntity that = (UserRatingBookEntity) o;
        return ratingBook == that.ratingBook;
    }

    @Override public int hashCode() {
        return Objects.hash(ratingBook);
    }
}
