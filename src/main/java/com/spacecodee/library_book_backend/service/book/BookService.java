package com.spacecodee.library_book_backend.service.book;

import com.spacecodee.library_book_backend.mappers.book.IBookMapper;
import com.spacecodee.library_book_backend.mappers.book.IBookReadMapper;
import com.spacecodee.library_book_backend.model.dto.book.BookAndCategoryDto;
import com.spacecodee.library_book_backend.model.dto.book.ShowBookDto;
import com.spacecodee.library_book_backend.model.vo.book.BookVo;
import com.spacecodee.library_book_backend.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final IBookRepository iBookRepository;

    public BookService(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    @Override
    public List<BookAndCategoryDto> findAll() {
        return IBookReadMapper.INSTANCE.setBooksAndCategoryDto(this.iBookRepository.findAll());
    }

    @Override
    public Optional<BookAndCategoryDto> getById(int id) {
        return this.iBookRepository
                .findById(id).map(IBookReadMapper.INSTANCE::toDto)
                .or(Optional::empty);
    }

    @Override
    public Optional<ShowBookDto> getByBookAndClientId(int bookId) {
        return this.iBookRepository
                .findByBookId(bookId)
                .map(IBookReadMapper.INSTANCE::toDto)
                .or(Optional::empty);
    }

    @Override
    public boolean existById(int id) {
        return this.iBookRepository.existsById(id);
    }

    @Override
    public boolean existByName(String name) {
        return this.iBookRepository.existsByBookName(name);
    }

    @Override
    public void add(BookVo dto) {
        this.iBookRepository.save(IBookMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void update(BookVo dto) {
        this.iBookRepository.save(IBookMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void delete(int id) {
        this.iBookRepository.deleteById(id);
    }
}
