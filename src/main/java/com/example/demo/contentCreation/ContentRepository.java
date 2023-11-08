package com.example.demo.contentCreation;

import com.example.demo.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<ContentCreation, Long> {
    List<ContentCreation> findByEmail(String email);
    List<ContentCreation> findByUserId(Long userId);
}
