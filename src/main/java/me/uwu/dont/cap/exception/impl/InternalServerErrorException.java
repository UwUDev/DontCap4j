package me.uwu.dont.cap.exception.impl;

import me.uwu.dont.cap.exception.CaptchaException;

public class InternalServerErrorException extends CaptchaException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
