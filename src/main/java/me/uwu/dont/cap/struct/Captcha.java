package me.uwu.dont.cap.struct;

import lombok.Data;

public @Data class Captcha {
    private final String key, userAgent;
    private long time;
    private int failCount = 0;

    public String getTimeString() {
        return String.format("%02d.%ds", (time / 1000) % 60, time % 1000);
    }

    public boolean isChromeAgent() {
        return userAgent.contains("Chrome/1");
    }

    public String getChromeVersion() {
        return userAgent.split("Chrome/")[1].split(" ")[0];
    }

    public String getWebkitVersion() {
        return userAgent.split("AppleWebKit/")[1].split(" ")[0];
    }

    public String getSafariVersion() {
        return userAgent.split("Safari/")[1].split(" ")[0];
    }

    public String getMozillaVersion() {
        return userAgent.split("Mozilla/")[1].split(" ")[0];
    }
}
