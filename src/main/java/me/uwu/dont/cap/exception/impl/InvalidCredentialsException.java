package me.uwu.dont.cap.exception.impl;

import me.uwu.dont.cap.exception.CaptchaException;

public class InvalidCredentialsException extends CaptchaException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
