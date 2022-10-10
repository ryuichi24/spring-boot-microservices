package com.juniordevmind.bookapi.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.juniordevmind.bookapi.config.RabbitMQConfig;
import com.juniordevmind.bookapi.models.Author;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthorCreatedEventListener {

    @RabbitListener(queues = { RabbitMQConfig.QUEUE_AUTHOR_CREATED })
    public void handleMessage(Author message) {
        log.info("{} got triggered. got a message: {}", AuthorCreatedEventListener.class, message.toString());
    }
}
