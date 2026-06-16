package com.sweetshop.repository;

import com.sweetshop.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SweetRepository extends JpaRepository<Sweet, Long> {
}
