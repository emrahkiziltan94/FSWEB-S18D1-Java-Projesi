package com.workintech.s18d1.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BurgerTest {

    @Test
    void testBurgerSetAndGet() {
        Burger burger = new Burger();
        burger.setId(1L);
        burger.setName("Vegan Delight");
        burger.setPrice(8.99);
        burger.setIsVegan(true);
        burger.setBreadType(BreadType.WRAP);
        burger.setContents("Lettuce, Tomato, Vegan Patty, Avocado");

        
        assertEquals(1L, burger.getId());
        assertEquals("Vegan Delight", burger.getName());
        assertEquals(8.99, burger.getPrice());
        assertEquals(true, burger.getIsVegan());
        assertEquals(BreadType.WRAP, burger.getBreadType());
        assertEquals("Lettuce, Tomato, Vegan Patty, Avocado", burger.getContents());
    }

    @Test
    void testNoArgsConstructor() {
        
        Burger burger = new Burger();
        assertNull(burger.getName()); 
        
    }
}
