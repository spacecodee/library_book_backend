package com.spacecodee.library_book_backend.exceptions;

public class LoginAuthException extends RuntimeException {
    public LoginAuthException(String message) {
        super(message);
    }
}
