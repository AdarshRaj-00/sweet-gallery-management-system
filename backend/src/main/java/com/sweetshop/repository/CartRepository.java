package com.sweetshop.repository;

import com.sweetshop.model.CartItem;
import com.sweetshop.model.Sweet;
import com.sweetshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndSweet(User user, Sweet sweet);
    void deleteByUser(User user);
}
