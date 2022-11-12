package com.juniordevmind.authorapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniordevmind.authorapi.models.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {

}
