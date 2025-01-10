package com.example.apiawslambdaspringboot;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.LambdaLogger;
//import com.example.apiawslambdaspringboot.controller.MyController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//
//import org.springframework.context.ApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mockStatic;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(classes = MyLambdaFunction.class, properties = "server.port=8080")
//@ExtendWith(MockitoExtension.class)
//class MyLambdaFunctionTest {
//
//    @InjectMocks
//    private MyLambdaFunction lambdaFunction;
//
//    @MockBean
//    private ApplicationContext applicationContext;
//
//    @Mock
//    private MyController myController;
//
//    @Mock
//    private Context context;
//
//    @BeforeEach
//    void setUp() {
//        MyLambdaFunction.applicationContext = applicationContext;
//        when(applicationContext.getBean(MyController.class)).thenReturn(myController);
//        when(context.getLogger()).thenReturn(Mockito.mock(LambdaLogger.class));
//    }
//
//    @Test
//    void testHandleRequest() {
//        Object input = "Test Input";
//        ResponseEntity<String> mockedResponse = ResponseEntity.ok("Mocked Response");
//
//        when(myController.handleRequest(input)).thenReturn(mockedResponse);
//
//        ResponseEntity<String> result = (ResponseEntity<String>) lambdaFunction.handleRequest(input, context);
//
//        verify(context.getLogger()).log(Mockito.contains("Input: Test Input"));
//        verify(myController).handleRequest(input);
//        assertEquals("Mocked Response", result.getBody());
//    }
//
//    @Test
//    void testMain() {
//        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
//            mockedSpringApplication
//                    .when(() -> SpringApplication.run(MyLambdaFunction.class, new String[]{}))
//                    .thenReturn(null);
//
//            MyLambdaFunction.main(new String[]{});
//
//            mockedSpringApplication.verify(() -> SpringApplication.run(MyLambdaFunction.class, new String[]{}));
//        }
//    }
//}
