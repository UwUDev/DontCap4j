package me.uwu.dont.cap.exception.impl;

import me.uwu.dont.cap.exception.CaptchaException;

public class CaptchaTimoutException extends CaptchaException {
    public CaptchaTimoutException(String message) {
        super(message);
    }
}
