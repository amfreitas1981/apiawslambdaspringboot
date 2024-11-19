package com.example.apiawslambdaspringboot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MyControllerTest {

    private MyController myController;

    @BeforeEach
    void setUp() {
        myController = new MyController();
        ReflectionTestUtils.setField(myController, "serverPort", 8080); // Define o valor da porta
    }

    @Test
    void testHandleRequest() {
        Object input = "Sample Input";
        ResponseEntity<String> response = myController.handleRequest(input);

        assertNotNull(response);
        assertEquals("Hello, AWS Lambda! Server port: 8080", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}
