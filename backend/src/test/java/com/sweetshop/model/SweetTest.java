package com.sweetshop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SweetTest {

    @Test
    void shouldCreateSweetWithAllFields() {
        Sweet sweet = new Sweet(
                "Gulab Jamun",
                "Milk Sweet",
                20.0,
                10,
                "image-url"
        );

        assertEquals("Gulab Jamun", sweet.getName());
        assertEquals("Milk Sweet", sweet.getCategory());
        assertEquals(20.0, sweet.getPrice());
        assertEquals(10, sweet.getQuantity());
        assertEquals("image-url", sweet.getImageUrl());
    }
}
