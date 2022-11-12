package com.spacecodee.library_book_backend.repository;

import com.spacecodee.library_book_backend.entity.category.book.CategoryBookEntity;
import com.spacecodee.library_book_backend.entity.category.book.projections.CategoryBookIdNameBooksEntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryBookRepository extends JpaRepository<CategoryBookEntity, Integer> {

    List<CategoryBookIdNameBooksEntityInfo> findAllBy();

    boolean existsByCategoryBookName(String name);

    Optional<CategoryBookIdNameBooksEntityInfo> getByCategoryBookId(int id);
}