package com.juniordevmind.authorapi.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.juniordevmind.authorapi.dtos.AuthorDto;
import com.juniordevmind.authorapi.dtos.CreateAuthorDto;
import com.juniordevmind.authorapi.dtos.UpdateAuthorDto;
import com.juniordevmind.authorapi.models.Author;
import com.juniordevmind.authorapi.repositories.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository _authorRepository;

    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> authors = _authorRepository.findAll();
        List<AuthorDto> authorDtos = authors.stream().map(authorItem -> new AuthorDto(
                authorItem.getId(),
                authorItem.getName(),
                authorItem.getDescription())).toList();
        return authorDtos;
    }

    @Override
    public AuthorDto getAuthor(UUID id) {
        Author author = _findAuthorById(id);
        return new AuthorDto(
                author.getId(),
                author.getName(),
                author.getDescription());
    }

    @Override
    public AuthorDto createAuthor(CreateAuthorDto dto) {
        Author newAuthor = new Author();
        newAuthor.setName(dto.getName());
        newAuthor.setDescription(dto.getDescription());
        Author savedAuthor = _authorRepository.save(newAuthor);
        return new AuthorDto(
                savedAuthor.getId(),
                savedAuthor.getName(),
                savedAuthor.getDescription());

    }

    @Override
    public void deleteAuthor(UUID id) {
        Author author = _findAuthorById(id);
        _authorRepository.delete(author);
    }

    @Override
    public void updateAuthor(UpdateAuthorDto dto, UUID id) {
        Author found = _findAuthorById(id);

        if (Objects.nonNull(dto.getName())) {
            found.setName(dto.getName());
        }

        if (Objects.nonNull(dto.getDescription())) {
            found.setDescription(dto.getDescription());
        }

        _authorRepository.save(found);
    }

    private Author _findAuthorById(UUID id) {
        Optional<Author> result = _authorRepository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Not found with this ID: " + id);
        }

        return result.get();
    }

}
