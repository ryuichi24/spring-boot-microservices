package com.juniordevmind.bookapi.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.juniordevmind.bookapi.dtos.BookDto;
import com.juniordevmind.bookapi.dtos.CreateBookDto;
import com.juniordevmind.bookapi.dtos.UpdateBookDto;
import com.juniordevmind.bookapi.models.Book;
import com.juniordevmind.bookapi.repositories.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository _bookRepository;

    @Override
    public List<BookDto> getBooks() {
        List<Book> books = _bookRepository.findAll();
        List<BookDto> bookDtos = books.stream().map(bookItem -> (BookDto) BookDto.builder()
                .id(bookItem.getId())
                .title(bookItem.getTitle())
                .description(bookItem.getDescription())
                .createdAt(bookItem.getCreatedAt())
                .updatedAt(bookItem.getUpdatedAt())
                .build()).toList();
        return bookDtos;
    }

    @Override
    public BookDto getBook(UUID id) {
        Book book = _findBookById(id);
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    @Override
    public BookDto createBook(CreateBookDto dto) {
        Book newBook = new Book();
        newBook.setTitle(dto.getTitle());
        newBook.setDescription(dto.getDescription());
        Book savedBook = _bookRepository.save(newBook);
        return BookDto.builder()
                .id(savedBook.getId())
                .title(savedBook.getTitle())
                .description(savedBook.getDescription())
                .createdAt(savedBook.getCreatedAt())
                .updatedAt(savedBook.getUpdatedAt())
                .build();

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
            throw new RuntimeException("Not found with this ID: " + id);
        }

        return result.get();
    }

}
