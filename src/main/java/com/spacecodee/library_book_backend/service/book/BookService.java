package com.spacecodee.library_book_backend.service.book;

import com.spacecodee.library_book_backend.dto.book.BookLDto;
import com.spacecodee.library_book_backend.mappers.book.IBookEntityMapper;
import com.spacecodee.library_book_backend.repository.IBookRepository;
import com.spacecodee.library_book_backend.service.IGenericService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IGenericService<BookLDto, BookLDto, BookLDto> {

    private final IBookRepository iBookRepository;

    public BookService(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    @Override
    public List<BookLDto> getAll() {
        final List<BookLDto> booksLDto = new ArrayList<>();
        this.iBookRepository.findAll().forEach(bookEntity -> booksLDto.add(
                IBookEntityMapper.INSTANCE.entityToLDto(bookEntity)));

        return booksLDto;
    }

    @Override
    public Optional<BookLDto> getById(int id) {
        return this.iBookRepository
                .findById(id).or(Optional::empty)
                .map(IBookEntityMapper.INSTANCE::entityToLDto);
    }

    @Override
    public Optional<BookLDto> getByName(String name) {
        return this.iBookRepository
                .findByBookName(name).or(Optional::empty)
                .map(IBookEntityMapper.INSTANCE::entityToLDto);
    }

    @Override
    public boolean existById(int id) {
        return this.iBookRepository.existsById(id);
    }

    @Override
    public boolean existByName(String name) {
        return this.iBookRepository.existsByBookName(name);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void add(BookLDto dto) {
        this.iBookRepository.save(IBookEntityMapper.INSTANCE.lDtoToEntity(dto));
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public void update(BookLDto dto) {
        this.iBookRepository.save(IBookEntityMapper.INSTANCE.lDtoToEntity(dto));
    }

    @Override
    public void delete(int id) {
        this.iBookRepository.deleteById(id);
    }
}
