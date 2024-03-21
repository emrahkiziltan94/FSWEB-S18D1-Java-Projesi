package com.workintech.s18d1.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class BreadTypeTest {

    @Test
     void testEnumConstants() {
        
        assertTrue(BreadType.valueOf("BURGER") == BreadType.BURGER);
        assertTrue(BreadType.valueOf("WRAP") == BreadType.WRAP);
        assertTrue(BreadType.valueOf("DOUBLE") == BreadType.DOUBLE);
    }

    @Test
     void testEnumValues() {
        
        assertEquals(3, BreadType.values().length);
    }
}
