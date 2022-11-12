package com.spacecodee.library_book_backend.entity.book;

import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import com.spacecodee.library_book_backend.entity.rating.UserRatingBookEntity;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "book", schema = "public", catalog = "spacecodee")
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "book_id", nullable = false)
    private int bookId;
    @Basic
    @Column(name = "book_name", nullable = false)
    private String bookName;
    @Basic
    @Column(name = "book_pages", nullable = false)
    private int bookPages;
    @Basic
    @Column(name = "book_author", nullable = false)
    private String bookAuthor;
    @Basic
    @Column(name = "book_url_image", nullable = false)
    private String bookUrlImage;
    @Basic
    @Column(name = "book_url_pdf", nullable = false)
    private String bookUrlPdf;
    @Basic
    @Column(name = "book_description", nullable = false)
    private String bookDescription;
    @ManyToOne(targetEntity = CategoryBookEntity.class, optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "category_book_id", referencedColumnName = "category_book_id", nullable = false)
    @ToString.Exclude
    private CategoryBookEntity categoryBookEntity;
    @OneToMany(mappedBy = "bookEntity", targetEntity = UserRatingBookEntity.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<UserRatingBookEntity> ratingBooksEntity;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return bookId == that.bookId && bookPages == that.bookPages && Objects.equals(bookName, that.bookName)
                && Objects.equals(bookAuthor, that.bookAuthor)
                && Objects.equals(bookUrlImage, that.bookUrlImage)
                && Objects.equals(bookUrlPdf, that.bookUrlPdf)
                && Objects.equals(bookDescription, that.bookDescription);
    }

    @Override public int hashCode() {
        return Objects.hash(bookId, bookName, bookPages, bookAuthor, bookUrlImage, bookUrlPdf, bookDescription);
    }
}
