package com.sweetshop.service;

import com.sweetshop.model.CartItem;
import com.sweetshop.model.Sweet;
import com.sweetshop.model.User;
import com.sweetshop.repository.CartRepository;
import com.sweetshop.repository.SweetRepository;
import com.sweetshop.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final SweetRepository sweetRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       SweetRepository sweetRepository,
                       UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.sweetRepository = sweetRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found: " + username));
    }

    public List<CartItem> getCart() {
        return cartRepository.findByUser(getCurrentUser());
    }

    public CartItem addToCart(Long sweetId, int quantity) {
        User user = getCurrentUser();
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + sweetId));

        CartItem cartItem = cartRepository.findByUserAndSweet(user, sweet)
                .orElse(new CartItem(user, sweet, 0));

        int newQuantity = cartItem.getQuantity() + quantity;
        
        // Optionally validate stock limits
        if (sweet.getQuantity() < newQuantity) {
            newQuantity = sweet.getQuantity(); // cap to max available stock
        }
        
        if (newQuantity <= 0) {
            newQuantity = 1; // minimum 1 if adding
        }

        cartItem.setQuantity(newQuantity);
        return cartRepository.save(cartItem);
    }

    public CartItem updateQuantity(Long sweetId, int quantity) {
        User user = getCurrentUser();
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + sweetId));

        CartItem cartItem = cartRepository.findByUserAndSweet(user, sweet)
                .orElseThrow(() -> new RuntimeException("Item not found in your cart"));

        if (quantity <= 0) {
            cartRepository.delete(cartItem);
            return null;
        }

        // Cap to stock limits
        if (sweet.getQuantity() < quantity) {
            quantity = sweet.getQuantity();
        }

        cartItem.setQuantity(quantity);
        return cartRepository.save(cartItem);
    }

    public void removeFromCart(Long sweetId) {
        User user = getCurrentUser();
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + sweetId));

        CartItem cartItem = cartRepository.findByUserAndSweet(user, sweet)
                .orElseThrow(() -> new RuntimeException("Item not found in your cart"));

        cartRepository.delete(cartItem);
    }

    public void clearCart() {
        cartRepository.deleteByUser(getCurrentUser());
    }
}
