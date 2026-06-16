package com.sweetshop.controller;

import com.sweetshop.model.Sweet;
import com.sweetshop.service.SweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SweetController.class)
class SweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SweetService sweetService;

    @Test
    @WithMockUser
    void shouldReturnAllSweets() throws Exception {
        when(sweetService.getAllSweets()).thenReturn(
                List.of(new Sweet("Gulab Jamun", "Milk Sweet", 20, 10, "img"))
        );

        mockMvc.perform(get("/api/sweets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gulab Jamun"));
    }
}

