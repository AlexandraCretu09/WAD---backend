package com.example.demo.Product;

import com.example.demo.contentCreation.ContentCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByEmail(String email);
    Product findById(long productId);
    //List<Product> findByUserID(long id);
}
