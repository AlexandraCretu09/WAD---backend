package com.example.demo.Interpretation;

import com.example.demo.Product.Product;
import com.example.demo.contentCreation.ContentCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterpretationRepository extends JpaRepository<Interpretation, Long> {
    List<Interpretation> findByEmail(String email);
}
