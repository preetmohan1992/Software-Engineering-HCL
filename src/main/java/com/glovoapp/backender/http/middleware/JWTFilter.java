package com.glovoapp.backender.http.middleware;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glovoapp.backender.http.handlers.UnauthorizedException;
import com.glovoapp.backender.http.responses.ErrorResponse;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
@Order(1)
public class JWTFilter extends OncePerRequestFilter {

    private final String jwtIssuer;
    private final String jwtSecret;

    public JWTFilter(
            @Value("${glovoapp.jwt.issuer}") String jwtIssuer,
            @Value("${glovoapp.jwt.secret}") String jwtSecret
    ) {
        this.jwtIssuer = jwtIssuer;
        this.jwtSecret = jwtSecret;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String authHeader = httpServletRequest.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new UnauthorizedException("Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);

            assertTokenIsValid(token);

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (UnauthorizedException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    DateTime.now().toDate(),
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage(),
                    Collections.emptyList()
            );

            httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        }
    }

    private void assertTokenIsValid(String token) {
        try {
            JWTVerifier verifier = JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer(jwtIssuer)
                    .build();

            verifier.verify(token);
        } catch (JWTVerificationException exception){
            throw new UnauthorizedException("Invalid token");
        }
    }
}
