package me.uwu.dont.cap.struct;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProxyType {
    HTTP("http"),
    SOCKS4("socks4"),
    SOCKS5("socks5");

    private final String name;
}
