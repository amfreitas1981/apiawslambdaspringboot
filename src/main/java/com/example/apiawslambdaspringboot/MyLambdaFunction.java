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
        applicationContext = SpringApplication.run(MyLambdaFunction.class, args);
    }

    @Override
    public Object handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);

        return applicationContext.getBean(MyController.class).handleRequest(input);
    }
}
