package com.juniordevmind.bookapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniordevmind.bookapi.models.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
