package com.spacecodee.library_book_backend.entity.people;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "people", schema = "public", catalog = "spacecodee")
public class PeopleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "people_id", nullable = false)
    private int peopleId;
    @Basic
    @Column(name = "people_name", nullable = false, length = 200)
    private String peopleName;
    @Basic
    @Column(name = "people_surname", nullable = false, length = 200)
    private String peopleSurname;
    @Basic
    @Column(name = "people_phone")
    private int peoplePhone;
    @Basic
    @Column(name = "people_address", length = 100)
    private String peopleAddress;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleEntity that = (PeopleEntity) o;
        return peopleId == that.peopleId && Objects.equals(peopleName, that.peopleName)
                && Objects.equals(peopleSurname, that.peopleSurname)
                && Objects.equals(peoplePhone, that.peoplePhone)
                && Objects.equals(peopleAddress, that.peopleAddress);
    }

    @Override public int hashCode() {
        return Objects.hash(peopleId, peopleName, peopleSurname, peoplePhone, peopleAddress);
    }
}
