package com.springboot.interview.basic.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponse {
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;

}
