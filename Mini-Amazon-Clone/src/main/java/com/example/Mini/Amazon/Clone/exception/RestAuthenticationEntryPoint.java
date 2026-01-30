package com.example.Mini.Amazon.Clone.exception;

import com.example.Mini.Amazon.Clone.dto.requests.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse error = new ErrorResponse(
                HttpServletResponse.SC_UNAUTHORIZED,
                authException.getMessage() != null ? authException.getMessage() : "Unauthorized",
                "UNAUTHORIZED",
                LocalDateTime.now()
        );
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), error);
    }
}
