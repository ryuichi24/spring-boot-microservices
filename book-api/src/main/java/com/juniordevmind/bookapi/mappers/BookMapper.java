package com.juniordevmind.bookapi.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.juniordevmind.bookapi.dtos.BookDto;
import com.juniordevmind.bookapi.models.Book;
import com.juniordevmind.shared.domain.BookEventDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final ModelMapper _modelMapper;

    public BookDto toDto(Book entity) {
        return _modelMapper.map(entity, BookDto.class);
    }

    public BookEventDto toEventDto(Book entity) {
        return _modelMapper.map(entity, BookEventDto.class);
    }
}
