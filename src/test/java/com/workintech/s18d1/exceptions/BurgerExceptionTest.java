package com.workintech.s18d1.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class BurgerExceptionTest {

    @Test
    void testBurgerExceptionCreation() {
        String expectedMessage = "Test exception message";
        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;

        BurgerException exception = new BurgerException(expectedMessage, expectedStatus);

        
        assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the expected value.");
        assertEquals(expectedStatus, exception.getHttpStatus(), "The HttpStatus should match the expected value.");
    }

    @Test
    void testBurgerExceptionIsRuntimeException() {
        BurgerException exception = new BurgerException("Test", HttpStatus.BAD_REQUEST);

        
        assertTrue(exception instanceof RuntimeException, "BurgerException should be an instance of RuntimeException.");
    }
}
