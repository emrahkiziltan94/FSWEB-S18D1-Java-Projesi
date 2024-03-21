package com.workintech.s18d1.exceptions;

import com.workintech.s18d1.controller.BurgerController;
import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.exceptions.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {BurgerController.class, GlobalExceptionHandler.class})
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BurgerDao burgerDao;

    @Test
    public void testBurgerNotFoundException() throws Exception {
        given(burgerDao.findById(anyLong())).willThrow(new BurgerException("Burger not found", HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/burger/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Burger not found"));
    }

    @Test
    public void testGenericException() throws Exception {
        given(burgerDao.findById(anyLong())).willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/burger/{id}", 2L))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Unexpected error"));
    }
}
