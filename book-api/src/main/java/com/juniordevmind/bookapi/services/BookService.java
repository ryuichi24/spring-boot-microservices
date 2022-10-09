package com.juniordevmind.bookapi.services;

import java.util.List;
import java.util.UUID;

import com.juniordevmind.bookapi.dtos.BookDto;
import com.juniordevmind.bookapi.dtos.CreateBookDto;
import com.juniordevmind.bookapi.dtos.UpdateBookDto;

public interface BookService {
    public List<BookDto> getBooks();

    public BookDto getBook(UUID id);

    public BookDto createBook(CreateBookDto dto);

    public void deleteBook(UUID id);

    public void updateBook(UpdateBookDto dto, UUID id);
}
