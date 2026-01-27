package com.example.Mini.Amazon.Clone.config;

import com.example.Mini.Amazon.Clone.dto.requests.responses.ApiResponse;
import com.example.Mini.Amazon.Clone.services.Impl.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final Logger log =
            LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("JWT Filter started for request: {} {}", request.getMethod(), request.getRequestURI());

        final String authHeader = request.getHeader("Authorization"); //Authorization: Bearer eyJhbGciOiJIUzI1NiJ9... Reading Authorization Header

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = authHeader.substring(7);  //Removes "Bearer " Extracts username/email from token claims
            String username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                if (jwtService.validateToken(jwt)) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails. getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    log.info("User authenticated successfully: {}", username);
                }
            }
            filterChain.doFilter(request, response); //It means “pass the request to the next filter (or controller) in the chain.”
            // Request -> Filter 1 -> Filter 2 (JWT Filter) -> Spring Security Filter -> Controller ->Response

        }
        catch (ExpiredJwtException e) {
            log.warn("JWT token expired", e);
            writeResponse(response, new ResponseEntity<>(
                            new ApiResponse<>("false", "JWT token expired"),
                            HttpStatus.UNAUTHORIZED));

        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e);
            writeResponse(response, new ResponseEntity<>(
                            new ApiResponse<>("false", "Invalid JWT token"),
                            HttpStatus.UNAUTHORIZED));

        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty", e);
            writeResponse(response, new ResponseEntity<>(
                            new ApiResponse<>("false", "JWT claims string is empty"),
                            HttpStatus.BAD_REQUEST));

        } catch (SignatureException e) {
            log.error("Invalid JWT signature", e);
            writeResponse(response, new ResponseEntity<>(
                    new ApiResponse<>("false", "Invalid JWT token"), HttpStatus.UNAUTHORIZED));
        }
    }

    private void writeResponse(HttpServletResponse response, ResponseEntity<ApiResponse<?>> entity) throws IOException {
        response.setStatus(entity.getStatusCode().value());
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registeredModules();
        response.getWriter().write(mapper.writeValueAsString(entity.getBody()));
    }
}