package com.sweetshop.service;

import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SweetServiceTest {

    private final SweetRepository sweetRepository =
            Mockito.mock(SweetRepository.class);

    private final SweetService sweetService =
            new SweetService(sweetRepository);

    @Test
    void shouldReturnAllSweets() {
        Mockito.when(sweetRepository.findAll())
                .thenReturn(List.of(
                        new Sweet("Gulab Jamun", "Milk Sweet", 20, 10, "img"),
                        new Sweet("Rasgulla", "Bengali Sweet", 15, 0, "img")
                ));

        List<Sweet> sweets = sweetService.getAllSweets();

        assertEquals(2, sweets.size());
    }
}
