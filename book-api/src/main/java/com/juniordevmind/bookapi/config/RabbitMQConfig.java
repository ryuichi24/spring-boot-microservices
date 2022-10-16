package com.juniordevmind.bookapi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
