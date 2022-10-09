package com.juniordevmind.bookapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juniordevmind.bookapi.dtos.BookDto;
import com.juniordevmind.bookapi.dtos.CreateBookDto;
import com.juniordevmind.bookapi.dtos.UpdateBookDto;
import com.juniordevmind.bookapi.services.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = BookController.BASE_URL)
@RequiredArgsConstructor
public class BookController {
    public static final String BASE_URL = "/api/v1/books";

    private final BookService _bookService;

    @GetMapping("")
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(_bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable UUID id) {
        return ResponseEntity.ok(_bookService.getBook(id));
    }

    @PostMapping("")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookDto dto) {
        BookDto newBook = _bookService.createBook(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBook.getId()).toUri();
        return ResponseEntity.created(location).body(newBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id) {
        _bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable UUID id, @Valid @RequestBody UpdateBookDto dto) {
        _bookService.updateBook(dto, id);
        return ResponseEntity.noContent().build();
    }
}
