package com.juniordevmind.shared.models;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class CustomMessage<T> {
    private UUID messageId;
    private LocalDateTime messageDate;
    private T payload;
}
