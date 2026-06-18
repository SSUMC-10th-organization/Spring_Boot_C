package com.example.umc10th.common.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@RestController
@RequestMapping("/api/instance")
public class InstanceController {

    @Value("${server.port:8080}")
    private String port;

    @GetMapping
    public Map<String, String> getInstance() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        return Map.of(
                "hostname", hostname,
                "port", port,
                "message", "요청을 처리한 서버: " + hostname + " (port: " + port + ")"
        );
    }
}
