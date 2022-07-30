package com.glovoapp.backender.http.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.glovoapp.backender.http.handlers.UnauthorizedException;
import com.glovoapp.backender.http.requests.LoginRequest;
import com.glovoapp.backender.http.responses.TokenResponse;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final String jwtIssuer;
    private final String jwtSecret;
    private final int ttlMinutes;

    public AuthController(
            @Value("${glovoapp.jwt.issuer}") String jwtIssuer,
            @Value("${glovoapp.jwt.secret}") String jwtSecret,
            @Value("${glovoapp.jwt.ttl-minutes}") int ttlMinutes
    ) {
        this.jwtIssuer = jwtIssuer;
        this.jwtSecret = jwtSecret;
        this.ttlMinutes = ttlMinutes;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    TokenResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        if (!loginRequest.getUsername().equals("test") || !loginRequest.getPassword().equals("1234")) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String token = JWT.create()
                .withIssuer(jwtIssuer)
                .withExpiresAt(DateTime.now().plusMinutes(ttlMinutes).toDate())
                .sign(Algorithm.HMAC256(jwtSecret));

        return new TokenResponse(token);
    }
}
