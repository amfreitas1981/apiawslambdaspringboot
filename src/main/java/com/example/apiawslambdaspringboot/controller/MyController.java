package com.example.apiawslambdaspringboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    @Value("${server.port}")
    private int serverPort;

    public ResponseEntity<String> handleRequest(Object input) {
        String response = "Hello, AWS Lambda! Server port: " + serverPort;
        return ResponseEntity.ok(response);
    }
}
