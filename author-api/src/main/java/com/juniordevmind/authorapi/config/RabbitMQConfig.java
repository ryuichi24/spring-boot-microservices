package com.juniordevmind.authorapi.config;

import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juniordevmind.shared.constants.RabbitMQKeys;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_BOOK_CREATED = "author-api.message.queue.book.created";
    public static final String QUEUE_BOOK_DELETED = "author-api.message.queue.book.deleted";
    public static final String QUEUE_BOOK_UPDATED = "author-api.message.queue.book.updated";

    // producer
    @Bean
    public FanoutExchange authorCreatedExchange() {
        return new FanoutExchange(RabbitMQKeys.AUTHOR_CREATED_EXCHANGE);
    }

    @Bean
    public FanoutExchange authorDeletedExchange() {
        return new FanoutExchange(RabbitMQKeys.AUTHOR_DELETED_EXCHANGE);
    }

    @Bean
    public FanoutExchange authorUpdatedExchange() {
        return new FanoutExchange(RabbitMQKeys.AUTHOR_UPDATED_EXCHANGE);
    }

    // consumer

    // book created event
    @Bean
    public Queue bookCreatedQueue() {
        return new Queue(QUEUE_BOOK_CREATED);
    }

    @Bean
    public FanoutExchange bookCreatedExchange() {
        return new FanoutExchange(RabbitMQKeys.BOOK_CREATED_EXCHANGE);
    }

    @Bean
    public Binding bookCreatedBinding(Queue bookCreatedQueue, FanoutExchange bookCreatedExchange) {
        return BindingBuilder
                .bind(bookCreatedQueue)
                .to(bookCreatedExchange);
    }

    // book deleted event
    @Bean
    public Queue bookDeletedQueue() {
        return new Queue(QUEUE_BOOK_DELETED);
    }

    @Bean
    public FanoutExchange bookDeletedExchange() {
        return new FanoutExchange(RabbitMQKeys.BOOK_DELETED_EXCHANGE);
    }

    @Bean
    public Binding bookDeletedBinding(Queue bookDeletedQueue, FanoutExchange bookDeletedExchange) {
        return BindingBuilder
                .bind(bookDeletedQueue)
                .to(bookDeletedExchange);
    }

    // book updated event
    @Bean
    public Queue bookUpdatedQueue() {
        return new Queue(QUEUE_BOOK_UPDATED);
    }

    @Bean
    public FanoutExchange bookUpdatedExchange() {
        return new FanoutExchange(RabbitMQKeys.BOOK_UPDATED_EXCHANGE);
    }

    @Bean
    public Binding bookUpdatedBinding(Queue bookUpdatedQueue, FanoutExchange bookUpdatedExchange) {
        return BindingBuilder
                .bind(bookUpdatedQueue)
                .to(bookUpdatedExchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
