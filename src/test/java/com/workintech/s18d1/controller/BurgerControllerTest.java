package com.workintech.s18d1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BurgerController.class)
class BurgerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BurgerDao burgerDao;

    private Burger sampleBurger;

    @BeforeEach
    void setUp() {
        sampleBurger = new Burger();
        sampleBurger.setId(1L);
        sampleBurger.setName("Classic Burger");
        sampleBurger.setPrice(7.99);
        sampleBurger.setIsVegan(false);
        sampleBurger.setBreadType(BreadType.BURGER);
        sampleBurger.setContents("Beef, Lettuce, Tomato, Cheese");
    }

    @Test
    void testSaveBurger() throws Exception {
        given(burgerDao.save(any())).willReturn(sampleBurger);

        mockMvc.perform(post("/burger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleBurger)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(sampleBurger.getName())));
    }

    @Test
    void testFindAllBurgers() throws Exception {
        List<Burger> burgers = Arrays.asList(sampleBurger);
        given(burgerDao.findAll()).willReturn(burgers);

        mockMvc.perform(get("/burger"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(sampleBurger.getName())));
    }

    @Test
    void testGetBurgerById() throws Exception {
        given(burgerDao.findById(sampleBurger.getId())).willReturn(sampleBurger);

        mockMvc.perform(get("/burger/{id}", sampleBurger.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(sampleBurger.getName())));
    }

    @Test
    void testUpdateBurger() throws Exception {
        Burger updatedBurger = new Burger();
        updatedBurger.setId(1L);
        updatedBurger.setName("Updated Classic Burger");
        given(burgerDao.update(any())).willReturn(updatedBurger);

        mockMvc.perform(put("/burger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBurger)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(updatedBurger.getName())));
    }

    @Test
    void testRemoveBurger() throws Exception {

        given(burgerDao.remove(sampleBurger.getId())).willReturn(sampleBurger);

        mockMvc.perform(delete("/burger/{id}", sampleBurger.getId()))
                .andExpect(status().isOk());
    }


    @Test
    void testFindByBreadType() throws Exception {
        List<Burger> burgers = Arrays.asList(sampleBurger);
        given(burgerDao.findByBreadType(sampleBurger.getBreadType())).willReturn(burgers);

        mockMvc.perform(get("/burger/breadType/{breadType}", sampleBurger.getBreadType()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(sampleBurger.getName())));
    }

    @Test
    void testFindByPrice() throws Exception {
        List<Burger> burgers = Arrays.asList(sampleBurger);
        given(burgerDao.findByPrice(sampleBurger.getPrice().intValue())).willReturn(burgers);

        mockMvc.perform(get("/burger/price/{price}", sampleBurger.getPrice().intValue()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(sampleBurger.getName())));
    }

    @Test
    void testFindByContent() throws Exception {
        List<Burger> burgers = Arrays.asList(sampleBurger);
        given(burgerDao.findByContent("Cheese")).willReturn(burgers);

        mockMvc.perform(get("/burger/content/{content}", "Cheese"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].contents", containsString("Cheese")));
    }


}
