package com.juniordevmind.shared.constants;

public class RabbitMQKeys {
    // author
    public static final String AUTHOR_CREATED_EXCHANGE = "message.exchange.fanout.author.created";
    public static final String AUTHOR_DELETED_EXCHANGE = "message.exchange.fanout.author.deleted";
    public static final String AUTHOR_UPDATED_EXCHANGE = "message.exchange.fanout.author.updated";
    // book
    public static final String BOOK_CREATED_EXCHANGE = "message.exchange.fanout.book.created";
    public static final String BOOK_DELETED_EXCHANGE = "message.exchange.fanout.book.deleted";
    public static final String BOOK_UPDATED_EXCHANGE = "message.exchange.fanout.book.updated";
}
