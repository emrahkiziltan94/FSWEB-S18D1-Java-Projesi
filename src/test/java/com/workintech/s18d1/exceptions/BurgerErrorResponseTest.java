package com.workintech.s18d1.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BurgerErrorResponseTest {

    @Test
    void testBurgerErrorResponse() {
        String expectedMessage = "An error occurred";
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(expectedMessage);

        assertEquals(expectedMessage, errorResponse.getMessage(), "The retrieved message should match the expected message.");
    }
}
