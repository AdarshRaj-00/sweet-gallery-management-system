package com.sweetshop.service;

import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SweetService {

    private final SweetRepository sweetRepository;

    public SweetService(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    public Sweet updateSweet(Long id, Sweet updatedSweet) {
        Sweet existing = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        existing.setName(updatedSweet.getName());
        existing.setCategory(updatedSweet.getCategory());
        existing.setPrice(updatedSweet.getPrice());
        existing.setQuantity(updatedSweet.getQuantity());
        existing.setImageUrl(updatedSweet.getImageUrl());

        return sweetRepository.save(existing);
    }

    public void deleteSweet(Long id) {
        sweetRepository.deleteById(id);
    }

    public Sweet purchaseSweet(Long id) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        if (sweet.getQuantity() <= 0) {
            throw new RuntimeException("Sweet out of stock");
        }

        sweet.setQuantity(sweet.getQuantity() - 1);
        return sweetRepository.save(sweet);
    }
}
