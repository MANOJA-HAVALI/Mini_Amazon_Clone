package com.example.Mini.Amazon.Clone.dto.requests.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@JsonPropertyOrder({ "timestamp", "status", "message", "errorCode" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String errorCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;


}
