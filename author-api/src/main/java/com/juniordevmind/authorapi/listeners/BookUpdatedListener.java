package com.juniordevmind.authorapi.listeners;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.juniordevmind.authorapi.config.RabbitMQConfig;
import com.juniordevmind.authorapi.models.Author;
import com.juniordevmind.authorapi.models.Book;
import com.juniordevmind.authorapi.repositories.AuthorRepository;
import com.juniordevmind.authorapi.repositories.BookRepository;
import com.juniordevmind.shared.domain.BookEventDto;
import com.juniordevmind.shared.models.CustomMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookUpdatedListener {
    private final BookRepository _bookRepository;
    private final AuthorRepository _authorRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_BOOK_UPDATED)
    public void handleMessage(CustomMessage<BookEventDto> message) {
        log.info("{} got triggered. Message: {}", BookUpdatedListener.class, message.toString());
        BookEventDto bookEventDto = message.getPayload();
        Optional<Book> maybeExistingBook = _bookRepository.findById(bookEventDto.getId());
        if (maybeExistingBook.isEmpty()) {
            return;
        }
        Book existingBook = maybeExistingBook.get();
        existingBook.setTitle(bookEventDto.getTitle());
        existingBook.setDescription(bookEventDto.getDescription());

        List<Author> authors = _authorRepository.findAllByBooks(bookEventDto.getId());
        List<UUID> newAuthorIds = bookEventDto.getAuthors();
        for (Author authorItem : authors) {
            if (newAuthorIds.contains(authorItem.getId())) {
                if (!authorItem.getBooks().contains(bookEventDto.getId())) {
                    authorItem.getBooks().add(bookEventDto.getId());
                }
            } else {
                authorItem.getBooks().remove(bookEventDto.getId());
            }

        }
    }
}