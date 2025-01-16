package com.example.apiawslambdaspringboot;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.apiawslambdaspringboot.context.MockContext;
import com.example.apiawslambdaspringboot.controller.MyController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MyLambdaFunctionTest {

    @InjectMocks
    private MyLambdaFunction lambdaFunction;

    @Mock
    private MyController myController;

    @Mock
    private ApplicationContext applicationContext;

    private Context context;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        context = new MockContext();
        // Simula o ApplicationContext
        lambdaFunction.applicationContext = applicationContext;
        lenient().when(applicationContext.getBean(MyController.class)).thenReturn(myController);
        // Redireciona a saída do console para capturar a saída do método main
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        // Restaura a saída padrão do console
        System.setOut(originalOut);
    }

    @Test
    public void testHandleRequest() {
        // Define o comportamento esperado do método handleRequest do MyController
        when(myController.handleRequest("Teste de entrada"))
                .thenReturn(ResponseEntity.ok("Hello, AWS Lambda! Server port: 8080"));

        // Chama o método handleRequest da MyLambdaFunction
        ResponseEntity<String> response = (ResponseEntity<String>) lambdaFunction.handleRequest("Teste de entrada", context);

        // Verifica se o retorno está correto
        assertEquals("Hello, AWS Lambda! Server port: 8080", response.getBody());
    }

    @Test
    public void testMainMethod() {
        // Simula os argumentos para o método main
        String[] args = new String[]{};

        // Executa o método main
        MyLambdaFunction.main(args);

        // Verifica a saída do console
        String output = outContent.toString();
        assertTrue(output.contains("Valor Variável:"));
        assertTrue(output.contains("Retorno método 'handleRequest':"));
    }
}
