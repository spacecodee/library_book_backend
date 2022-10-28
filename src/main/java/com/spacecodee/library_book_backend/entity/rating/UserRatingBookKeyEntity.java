package com.spacecodee.library_book_backend.entity.rating;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class UserRatingBookKeyEntity implements Serializable {
    private static final long serialVersionUID = 3529694299394164619L;

    @Column(name = "rating_user_id")
    private int ratingUserId;
    @Column(name = "rating_book_id")
    private int ratingBookId;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRatingBookKeyEntity)) return false;
        UserRatingBookKeyEntity that = (UserRatingBookKeyEntity) o;
        return getRatingUserId() == that.getRatingUserId() && getRatingBookId() == that.getRatingBookId();
    }

    @Override public int hashCode() {
        return Objects.hash(getRatingUserId(), getRatingBookId());
    }
}
