package com.sweetshop.repository;

import com.sweetshop.model.Sweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SweetRepositoryTest {

    @Autowired
    private SweetRepository sweetRepository;

    @Test
    void saveAndFindById() {
        Sweet sweet = new Sweet("Gulab Jamun", "Milk Sweet", 20.0, 10, "image-url");
        Sweet saved = sweetRepository.save(sweet);
        assertThat(saved.getId()).isNotNull();
        assertThat(sweetRepository.findById(saved.getId())).isPresent();
    }
}
