package com.sweetshop.controller;

import com.sweetshop.model.CartItem;
import com.sweetshop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartRequest request) {
        CartItem item = cartService.addToCart(request.getSweetId(), request.getQuantity());
        return ResponseEntity.ok(item);
    }

    @PutMapping("/update")
    public ResponseEntity<CartItem> updateQuantity(@RequestBody CartRequest request) {
        CartItem item = cartService.updateQuantity(request.getSweetId(), request.getQuantity());
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/remove/{sweetId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long sweetId) {
        cartService.removeFromCart(sweetId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        cartService.clearCart();
        return ResponseEntity.ok("Cart cleared successfully");
    }

    // Inner DTO for Cart Requests
    public static class CartRequest {
        private Long sweetId;
        private int quantity;

        public CartRequest() {}

        public Long getSweetId() {
            return sweetId;
        }

        public void setSweetId(Long sweetId) {
            this.sweetId = sweetId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
