package com.juniordevmind.bookapi.config;

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
    public static final String QUEUE_AUTHOR_CREATED = "book-api.message.queue.author.created";
    public static final String QUEUE_AUTHOR_DELETED = "book-api.message.queue.author.deleted";
    public static final String QUEUE_AUTHOR_UPDATED = "book-api.message.queue.author.updated";

    // producer
    @Bean
    public FanoutExchange bookCreatedExchange() {
        return new FanoutExchange(RabbitMQKeys.BOOK_CREATED_EXCHANGE);
    }

    @Bean
    public FanoutExchange bookDeletedExchange() {
        return new FanoutExchange(RabbitMQKeys.BOOK_DELETED_EXCHANGE);
    }

    @Bean
    public FanoutExchange bookUpdatedExchange() {
        return new FanoutExchange(RabbitMQKeys.BOOK_UPDATED_EXCHANGE);
    }

    // consumer

    // author created event
    @Bean
    public Queue authorCreatedQueue() {
        return new Queue(QUEUE_AUTHOR_CREATED);
    }

    @Bean
    public FanoutExchange authorCreatedExchange() {
        return new FanoutExchange(RabbitMQKeys.AUTHOR_CREATED_EXCHANGE);
    }

    @Bean
    public Binding authorCreatedBinding(Queue authorCreatedQueue, FanoutExchange authorCreatedExchange) {
        return BindingBuilder
                .bind(authorCreatedQueue)
                .to(authorCreatedExchange);
    }

    // author deleted event
    @Bean
    public Queue authorDeletedQueue() {
        return new Queue(QUEUE_AUTHOR_DELETED);
    }

    @Bean
    public FanoutExchange authorDeletedExchange() {
        return new FanoutExchange(RabbitMQKeys.AUTHOR_DELETED_EXCHANGE);
    }

    @Bean
    public Binding authorDeletedBinding(Queue authorDeletedQueue, FanoutExchange authorDeletedExchange) {
        return BindingBuilder
                .bind(authorDeletedQueue)
                .to(authorDeletedExchange);
    }

    // author updated event
    @Bean
    public Queue authorUpdatedQueue() {
        return new Queue(QUEUE_AUTHOR_UPDATED);
    }

    @Bean
    public FanoutExchange authorUpdatedExchange() {
        return new FanoutExchange(RabbitMQKeys.AUTHOR_UPDATED_EXCHANGE);
    }

    @Bean
    public Binding authorUpdatedBinding(Queue authorUpdatedQueue, FanoutExchange authorUpdatedExchange) {
        return BindingBuilder
                .bind(authorUpdatedQueue)
                .to(authorUpdatedExchange);
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
