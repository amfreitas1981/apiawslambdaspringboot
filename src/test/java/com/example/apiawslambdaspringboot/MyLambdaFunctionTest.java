package com.example.apiawslambdaspringboot;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.example.apiawslambdaspringboot.controller.MyController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MyLambdaFunctionTest {

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private MyController myController;

    @Mock
    private Context context;

    @Mock
    private LambdaLogger lambdaLogger;

    private MyLambdaFunction lambdaFunction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializando a classe sob teste
        lambdaFunction = new MyLambdaFunction();

        // Configurando o contexto de aplicacao usando ReflectionTestUtils
        ReflectionTestUtils.setField(MyLambdaFunction.class, "applicationContext", applicationContext);

        // Mockando o comportamento do ApplicationContext
        lenient().when(applicationContext.getBean(MyController.class)).thenReturn(myController);

        // Mockando o comportamento do Context e do Logger
        LambdaLogger lambdaLogger = mock(LambdaLogger.class);
        lenient().when(context.getLogger()).thenReturn(lambdaLogger);

        // Configurando o mock do LambdaLogger para metodos void
        lenient().doNothing().when(lambdaLogger).log(anyString());
    }

    @Test
    void testMain() {
        // Mock o comportamento de SpringApplication.run
        try (MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {
            // Mock para o contexto configuravel do Spring
            ConfigurableApplicationContext mockContext = mock(ConfigurableApplicationContext.class);
            mockedSpringApplication.when(() -> SpringApplication.run(MyLambdaFunction.class, new String[]{}))
                    .thenReturn(mockContext);

            // Mock para o controller MyController
            MyController myControllerMock = mock(MyController.class);
            when(mockContext.getBean(MyController.class)).thenReturn(myControllerMock);
            when(myControllerMock.handleRequest(any())).thenReturn(ResponseEntity.ok("Mocked Response"));

            // Chamada do metodo main
            MyLambdaFunction.main(new String[]{});

            // Verifique se SpringApplication.run foi chamado corretamente
            mockedSpringApplication.verify(() -> SpringApplication.run(MyLambdaFunction.class, new String[]{}));

            // Verifique interacoes no controller
            verify(mockContext).getBean(MyController.class);
            verify(myControllerMock).handleRequest("Teste de entrada");
        }
    }

    @Test
    void testHandleRequest() {
        Object input = "Test Input";

        // Mockando o comportamento do MyController
        ResponseEntity<String> mockedResponse = ResponseEntity.ok("Mocked Response");
        when(myController.handleRequest(input)).thenReturn(mockedResponse);

        // Chamando o método a ser testado
        ResponseEntity<String> result = (ResponseEntity<String>) lambdaFunction.handleRequest(input, context);

        // Verificando se o logger foi invocado com a mensagem correta
        verify(context.getLogger()).log("Input: " + input);  // Verifica se o log foi chamado
        verify(myController).handleRequest(input);  // Verifica se o controller foi chamado

        // Validando a resposta
        assertEquals("Mocked Response", result.getBody());
    }
}
