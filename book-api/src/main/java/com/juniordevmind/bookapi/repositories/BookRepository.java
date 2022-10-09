package com.juniordevmind.bookapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniordevmind.bookapi.models.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {

}
