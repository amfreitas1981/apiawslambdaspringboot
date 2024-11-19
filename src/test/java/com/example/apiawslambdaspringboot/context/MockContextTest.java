package com.example.apiawslambdaspringboot.context;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MockContextTest {

    @Test
    void testGetAwsRequestId() {
        Context context = new MockContext();
        assertEquals("mock-request-id", context.getAwsRequestId());
    }

    @Test
    void testGetLogGroupName() {
        Context context = new MockContext();
        assertEquals("mock-log-group", context.getLogGroupName());
    }

    @Test
    void testGetLogStreamName() {
        Context context = new MockContext();
        assertEquals("mock-log-stream", context.getLogStreamName());
    }

    @Test
    void testGetFunctionName() {
        Context context = new MockContext();
        assertEquals("mock-function-name", context.getFunctionName());
    }

    @Test
    void testGetFunctionVersion() {
        Context context = new MockContext();
        assertEquals("1.0", context.getFunctionVersion());
    }

    @Test
    void testGetInvokedFunctionArn() {
        Context context = new MockContext();
        assertEquals("mock-arn", context.getInvokedFunctionArn());
    }

    @Test
    void testGetRemainingTimeInMillis() {
        Context context = new MockContext();
        assertEquals(1000, context.getRemainingTimeInMillis());
    }

    @Test
    void testGetMemoryLimitInMB() {
        Context context = new MockContext();
        assertEquals(128, context.getMemoryLimitInMB());
    }

    @Test
    void testGetIdentity() {
        Context context = new MockContext();
        assertNull(context.getIdentity());
    }

    @Test
    void testGetClientContext() {
        Context context = new MockContext();
        assertNull(context.getClientContext());
    }

    @Test
    void testLoggerLogString() {
        Context context = new MockContext();
        LambdaLogger logger = context.getLogger();
        assertNotNull(logger);

        // Captura a saída do logger
        logger.log("Test log message");
    }

    @Test
    void testLoggerLogByteArray() {
        Context context = new MockContext();
        LambdaLogger logger = context.getLogger();
        assertNotNull(logger);

        // Captura a saída do logger para byte array
        logger.log("Test log byte array".getBytes());
    }
}
