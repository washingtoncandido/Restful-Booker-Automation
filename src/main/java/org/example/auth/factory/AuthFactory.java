package org.example.auth.factory;

import org.example.auth.dto.AuthRequest;

public final class AuthFactory {

    private AuthFactory() {
    }

    public static AuthRequest validCredentials() {
        return new AuthRequest(
                "admin",
                "password123"
        );
    }

    public static AuthRequest invalidPassword() {
        return new AuthRequest(
                "admin",
                "password1232"
        );
    }

    public static AuthRequest invalidUsername() {
        return new AuthRequest(
                "admina",
                "password123"
        );
    }

    public static AuthRequest emptyCredentials() {
        return new AuthRequest(
                "",
                ""
        );
    }
}