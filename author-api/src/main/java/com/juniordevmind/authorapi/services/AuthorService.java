package com.juniordevmind.authorapi.services;

import java.util.List;
import java.util.UUID;

import com.juniordevmind.authorapi.dtos.AuthorDto;
import com.juniordevmind.authorapi.dtos.CreateAuthorDto;
import com.juniordevmind.authorapi.dtos.UpdateAuthorDto;

public interface AuthorService {
    public List<AuthorDto> getAuthors();

    public AuthorDto getAuthor(UUID id);

    public AuthorDto createAuthor(CreateAuthorDto dto);

    public void deleteAuthor(UUID id);

    public void updateAuthor(UpdateAuthorDto dto, UUID id);
}
