package me.uwu.dont.cap.struct;

import lombok.Getter;

@Getter
public class Proxy {
    private final ProxyType type;
    private final String host;
    private final int port;
    private final boolean auth;
    private String username;
    private String password;

    public Proxy(ProxyType proxyType, String host, int port) {
        this.type = proxyType;
        this.host = host;
        this.port = port;
        auth = false;
    }

    @SuppressWarnings("unused")
    public Proxy(ProxyType proxyType, String host, int port, String username, String password) {
        this.type = proxyType;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        auth = true;
    }

    @Override
    public String toString() {
        if (!auth)
            return host + ":" + port;
        return username + ":" + password + "@" + host + ":" + port;
    }
}
