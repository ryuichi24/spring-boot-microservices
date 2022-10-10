package com.juniordevmind.authorapi.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.juniordevmind.authorapi.dtos.AuthorDto;
import com.juniordevmind.authorapi.dtos.CreateAuthorDto;
import com.juniordevmind.authorapi.dtos.UpdateAuthorDto;
import com.juniordevmind.authorapi.mappers.AuthorMapper;
import com.juniordevmind.authorapi.models.Author;
import com.juniordevmind.authorapi.repositories.AuthorRepository;
import com.juniordevmind.shared.constants.RabbitMQKeys;
import com.juniordevmind.shared.errors.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository _authorRepository;
    private final AuthorMapper _authorMapper;
    private final RabbitTemplate _template;

    @Override
    public List<AuthorDto> getAuthors() {
        return _authorRepository.findAll().stream().map(author -> _authorMapper.toDto(author)).toList();
    }

    @Override
    public AuthorDto getAuthor(UUID id) {
        return _authorMapper.toDto(_findAuthorById(id));
    }

    @Override
    public AuthorDto createAuthor(CreateAuthorDto dto) {
        Author savedAuthor = _authorRepository.save(new Author(dto.getName(), dto.getDescription()));
        _template.convertAndSend(RabbitMQKeys.AUTHOR_CREATED_EXCHANGE, null, savedAuthor);
        return _authorMapper.toDto(savedAuthor);
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
            throw new NotFoundException("Not found with this ID: " + id);
        }

        return result.get();
    }

}
