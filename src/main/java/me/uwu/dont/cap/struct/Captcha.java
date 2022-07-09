package me.uwu.dont.cap.struct;

import lombok.Data;

public @Data class Captcha {
    private final String key;
    private long time;
    private int failCount = 0;

    public String getTimeString() {
        return String.format("%02d.%ds", (time / 1000) % 60, time % 1000);
    }
}
