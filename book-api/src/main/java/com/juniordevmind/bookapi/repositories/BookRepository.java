package com.juniordevmind.bookapi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniordevmind.bookapi.models.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findAllByAuthors(UUID authorId);
}
