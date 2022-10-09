package com.juniordevmind.bookapi.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.juniordevmind.bookapi.dtos.BookDto;
import com.juniordevmind.bookapi.dtos.CreateBookDto;
import com.juniordevmind.bookapi.dtos.UpdateBookDto;
import com.juniordevmind.bookapi.mappers.BookMapper;
import com.juniordevmind.bookapi.models.Book;
import com.juniordevmind.bookapi.repositories.BookRepository;
import com.juniordevmind.shared.errors.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository _bookRepository;
    private final BookMapper _bookMapper;

    @Override
    public List<BookDto> getBooks() {
        return _bookRepository.findAll().stream().map(bookItem -> _bookMapper.toDto(bookItem)).toList();
    }

    @Override
    public BookDto getBook(UUID id) {
        return _bookMapper.toDto(_findBookById(id));
    }

    @Override
    public BookDto createBook(CreateBookDto dto) {
        return _bookMapper.toDto(_bookRepository.save(new Book(dto.getTitle(), dto.getDescription())));
    }

    @Override
    public void deleteBook(UUID id) {
        Book book = _findBookById(id);
        _bookRepository.delete(book);
    }

    @Override
    public void updateBook(UpdateBookDto dto, UUID id) {
        Book found = _findBookById(id);

        if (Objects.nonNull(dto.getTitle())) {
            found.setTitle(dto.getTitle());
        }

        if (Objects.nonNull(dto.getDescription())) {
            found.setDescription(dto.getDescription());
        }

        _bookRepository.save(found);
    }

    private Book _findBookById(UUID id) {
        Optional<Book> result = _bookRepository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException("Not found with this ID: " + id);
        }

        return result.get();
    }

}
