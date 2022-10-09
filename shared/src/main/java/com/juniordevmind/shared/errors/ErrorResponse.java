package com.juniordevmind.shared.errors;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private List<String> messages;
    private HttpStatus httpStatus;
}
