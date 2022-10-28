package com.spacecodee.library_book_backend.service.category.book;

import com.spacecodee.library_book_backend.dto.category.book.CategoryBookADto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookLDto;
import com.spacecodee.library_book_backend.dto.category.book.CategoryBookUDto;
import com.spacecodee.library_book_backend.mappers.category.book.ICategoryBookEntityMapper;
import com.spacecodee.library_book_backend.repository.ICategoryBookRepository;
import com.spacecodee.library_book_backend.service.IGenericService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryBookService implements IGenericService<CategoryBookLDto, CategoryBookADto, CategoryBookUDto> {

    private final ICategoryBookRepository iCategoryBookRepository;

    public CategoryBookService(ICategoryBookRepository iCategoryBookRepository) {
        this.iCategoryBookRepository = iCategoryBookRepository;
    }

    @Override
    public List<CategoryBookLDto> getAll() {
        final List<CategoryBookLDto> categories = new ArrayList<>();
        this.iCategoryBookRepository.findAll().forEach(categoryBookEntity -> {
            if (!categoryBookEntity.getBooksEntity().isEmpty()) {
                categories.add(ICategoryBookEntityMapper.INSTANCE.entityToLDto(categoryBookEntity));
            }
        });

        return categories;
    }

    @Override
    public Optional<CategoryBookLDto> getById(int id) {
        return this.iCategoryBookRepository
                .findById(id).or(Optional::empty)
                .map(ICategoryBookEntityMapper.INSTANCE::entityToLDto);
    }

    @Override
    public Optional<CategoryBookLDto> getByName(String name) {
        return this.iCategoryBookRepository
                .findByCategoryBookName(name).or(Optional::empty)
                .map(ICategoryBookEntityMapper.INSTANCE::entityToLDto);
    }

    @Override
    public boolean existById(int id) {
        return this.iCategoryBookRepository.existsById(id);
    }

    @Override
    public boolean existByName(String name) {
        return this.iCategoryBookRepository.existsByCategoryBookName(name);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void add(CategoryBookADto dto) {
        this.iCategoryBookRepository.save(ICategoryBookEntityMapper.INSTANCE.aDtoToEntity(dto));
    }

    @Override
    public void update(CategoryBookUDto dto) {
        this.iCategoryBookRepository.save(ICategoryBookEntityMapper.INSTANCE.uDtoToEntity(dto));
    }

    @Override
    public void delete(int id) {
        this.iCategoryBookRepository.deleteById(id);
    }
}
