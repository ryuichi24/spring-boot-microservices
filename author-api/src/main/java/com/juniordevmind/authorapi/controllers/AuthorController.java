package com.juniordevmind.authorapi.controllers;

import java.net.URI;
import java.util.ArrayList;
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

import com.juniordevmind.authorapi.dtos.AuthorDto;
import com.juniordevmind.authorapi.dtos.CreateAuthorDto;
import com.juniordevmind.authorapi.dtos.UpdateAuthorDto;
import com.juniordevmind.authorapi.services.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = AuthorController.BASE_URL)
@RequiredArgsConstructor
public class AuthorController {
    public static final String BASE_URL = "/api/v1/authors";

    private final AuthorService _authorService;

    @GetMapping("")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return ResponseEntity.ok(new ArrayList<AuthorDto>(_authorService.getAuthors()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable UUID id) {
        return ResponseEntity.ok(_authorService.getAuthor(id));
    }

    @PostMapping("")
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody CreateAuthorDto dto) {
        AuthorDto newAuthor = _authorService.createAuthor(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newAuthor.getId()).toUri();
        return ResponseEntity.created(location).body(newAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable UUID id) {
        _authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable UUID id, @Valid @RequestBody UpdateAuthorDto dto) {
        _authorService.updateAuthor(dto, id);
        return ResponseEntity.noContent().build();
    }
}
