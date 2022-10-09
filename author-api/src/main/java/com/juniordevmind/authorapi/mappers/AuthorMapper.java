package com.juniordevmind.authorapi.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.juniordevmind.authorapi.dtos.AuthorDto;
import com.juniordevmind.authorapi.models.Author;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthorMapper {
    private final ModelMapper _modelMapper;

    public AuthorDto toDto(Author entity) {
        return _modelMapper.map(entity, AuthorDto.class);
    }
}
