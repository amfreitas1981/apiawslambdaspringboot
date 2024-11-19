package com.example.apiawslambdaspringboot;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.apiawslambdaspringboot.controller.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.apiawslambdaspringboot")
public class MyLambdaFunction implements RequestHandler<Object, Object> {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // Inicia o contexto do Spring
        applicationContext = SpringApplication.run(MyLambdaFunction.class, args);

        // Imprime o valor da variavel applicationContext
        System.out.println("Valor Variável: " + applicationContext);

        // Cria uma instancia de MyLambdaFunction
        MyLambdaFunction lambdaFunction = new MyLambdaFunction();

        // Simula os argumentos para handleRequest
        Object input = "Teste de entrada"; // Simule aqui o objeto de entrada
        Context context = new MockContext(); // Use uma classe mock para simular o contexto

        // Chama o metodo handleRequest e imprime o retorno
        Object retorno = lambdaFunction.handleRequest(input, context);
        System.out.println("Retorno método 'handleRequest': " + retorno);
    }

    @Override
    public Object handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);

        return applicationContext.getBean(MyController.class).handleRequest(input);
    }
}
