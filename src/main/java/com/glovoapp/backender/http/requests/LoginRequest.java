package com.glovoapp.backender.http.requests;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "The [username] is required.")
    private String username;

    @NotBlank(message = "The [password] is required.")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
