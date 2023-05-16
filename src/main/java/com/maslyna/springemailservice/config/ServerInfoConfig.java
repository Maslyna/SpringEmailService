package com.maslyna.springemailservice.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@AllArgsConstructor
@Configuration
public class ServerInfoConfig {
    private Environment env;

    public String getServerPort() {
        return env.getProperty("server.port");
    }
    public String getIpAddress() {
        InetAddress ip = null;
        try {
            return ip.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    public String getFullServerAddress() {
        return getIpAddress() + ":" + getServerPort();
    }

}
