package com.sweetshop.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sweets")
public class Sweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    // Required by JPA
    protected Sweet() {
    }

    // Used by tests & service layer
    public Sweet(String name, String category, double price, int quantity, String imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Equality based on id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sweet)) return false;
        Sweet sweet = (Sweet) o;
        return Objects.equals(id, sweet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
