package com.juniordevmind.bookapi.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.juniordevmind.bookapi.dtos.AuthorDto;
import com.juniordevmind.bookapi.models.Author;
import com.juniordevmind.shared.domain.AuthorEventDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthorMapper {
    private final ModelMapper _modelMapper;

    public Author toEntity(AuthorEventDto eventDto) {
        return _modelMapper.map(eventDto, Author.class);
    }

    public AuthorDto toDto(Author entity) {
        return _modelMapper.map(entity, AuthorDto.class);
    }

}
