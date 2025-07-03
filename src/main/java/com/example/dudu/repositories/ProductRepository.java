package com.example.dudu.repositories;

import com.example.dudu.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsActive(Boolean isActive);
    List<Product> findByCategoryAndIsActive(String category, Boolean isActive);
}
