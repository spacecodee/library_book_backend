package com.spacecodee.library_book_backend.service.category.book;

import com.spacecodee.library_book_backend.mappers.category.book.ICategoryBookMapper;
import com.spacecodee.library_book_backend.mappers.category.book.ICategoryBookReadMapper;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookAndBookDto;
import com.spacecodee.library_book_backend.model.dto.category.book.CategoryBookDto;
import com.spacecodee.library_book_backend.model.vo.category.book.CategoryBookVo;
import com.spacecodee.library_book_backend.repository.ICategoryBookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryBookService implements ICategoryBookService {

    private final ICategoryBookRepository iCategoryBookRepository;

    public CategoryBookService(ICategoryBookRepository iCategoryBookRepository) {
        this.iCategoryBookRepository = iCategoryBookRepository;
    }

    @Override
    public List<CategoryBookDto> getAll() {
        final List<CategoryBookDto> categories = new ArrayList<>();
        this.iCategoryBookRepository.findAll().forEach(categoryBookEntity -> categories.add(
                ICategoryBookReadMapper.INSTANCE.toDto(categoryBookEntity)));

        return categories;
    }

    @Override
    public Optional<CategoryBookDto> getByCategoryId(int id) {
        return this.iCategoryBookRepository.findById(id).map(ICategoryBookReadMapper.INSTANCE::toDto);
    }

    @Override
    public List<CategoryBookAndBookDto> findAll() {
        final List<CategoryBookAndBookDto> categories = new ArrayList<>();
        this.iCategoryBookRepository
                .findAllBy()
                .forEach(entity -> {
                    if (!entity.getBooksEntity().isEmpty()) {
                        categories.add(ICategoryBookReadMapper.INSTANCE.toRDto(entity));
                    }
                });

        return categories;
    }

    @Override
    public Optional<CategoryBookAndBookDto> getByIdCategoryBook(int id) {
        return this.iCategoryBookRepository
                .getByCategoryBookId(id).or(Optional::empty)
                .map(ICategoryBookReadMapper.INSTANCE::toRDto);
    }

    @Override
    public boolean existByCategoryId(int id) {
        return this.iCategoryBookRepository.existsById(id);
    }

    @Override
    public boolean existByCategoryName(String name) {
        return this.iCategoryBookRepository.existsByCategoryBookName(name);
    }

    @Override
    public void add(CategoryBookVo dto) {
        this.iCategoryBookRepository.save(ICategoryBookMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void update(CategoryBookVo dto) {
        this.iCategoryBookRepository.save(ICategoryBookMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void delete(int id) {
        this.iCategoryBookRepository.deleteById(id);
    }
}
