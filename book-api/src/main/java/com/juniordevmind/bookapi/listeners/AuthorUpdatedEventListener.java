package com.juniordevmind.bookapi.listeners;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.juniordevmind.bookapi.config.RabbitMQConfig;
import com.juniordevmind.bookapi.models.Author;
import com.juniordevmind.bookapi.repositories.AuthorRepository;
import com.juniordevmind.shared.domain.AuthorEventDto;
import com.juniordevmind.shared.models.CustomMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthorUpdatedEventListener {
    private final AuthorRepository _authorRepository;

    @RabbitListener(queues = { RabbitMQConfig.QUEUE_AUTHOR_UPDATED })
    public void handleMessage(CustomMessage<AuthorEventDto> message) {
        log.info("{} got triggered. Message: {}", AuthorUpdatedEventListener.class, message.toString());
        AuthorEventDto authorEventDto = message.getPayload();
        Optional<Author> maybeExistingAuthor = _authorRepository.findById(authorEventDto.getId());
        if (maybeExistingAuthor.isEmpty()) {
            return;
        }
        Author existingAuthor = maybeExistingAuthor.get();
        existingAuthor.setName(authorEventDto.getName());
        existingAuthor.setDescription(authorEventDto.getDescription());
    }
}
