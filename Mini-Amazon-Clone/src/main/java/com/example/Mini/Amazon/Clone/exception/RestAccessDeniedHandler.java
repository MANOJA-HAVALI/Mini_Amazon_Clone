package com.example.Mini.Amazon.Clone.exception;

import com.example.Mini.Amazon.Clone.dto.requests.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ErrorResponse error = new ErrorResponse(
                HttpServletResponse.SC_FORBIDDEN,
                accessDeniedException.getMessage() != null ? accessDeniedException.getMessage() : "Access is denied",
                "FORBIDDEN",
                LocalDateTime.now()
        );

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        mapper.writeValue(response.getWriter(), error);
    }
}
