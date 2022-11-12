package com.juniordevmind.bookapi.listeners;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.juniordevmind.bookapi.config.RabbitMQConfig;
import com.juniordevmind.bookapi.models.Author;
import com.juniordevmind.bookapi.models.Book;
import com.juniordevmind.bookapi.repositories.AuthorRepository;
import com.juniordevmind.bookapi.repositories.BookRepository;
import com.juniordevmind.shared.domain.AuthorEventDto;
import com.juniordevmind.shared.models.CustomMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthorDeletedEventListener {
    private final BookRepository _bookRepository;
    private final AuthorRepository _authorRepository;

    @RabbitListener(queues = { RabbitMQConfig.QUEUE_AUTHOR_DELETED })
    public void handleMessage(CustomMessage<AuthorEventDto> message) {
        log.info("{} got triggered. got a message: {}", AuthorDeletedEventListener.class,
        message.getPayload().toString());

        AuthorEventDto authorEventDto = message.getPayload();
        Optional<Author> existingAuthor = _authorRepository.findById(authorEventDto.getId());
        if (existingAuthor.isEmpty()) {
            return;
        }
        _authorRepository.delete(existingAuthor.get());

        List<Book> books = _bookRepository.findAllByAuthors(authorEventDto.getId());

        for (Book bookItem : books) {
            bookItem.getAuthors().remove(authorEventDto.getId());
        }
    }
}
