package com.example.demo.Product;


import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public void deleteProduct(long productID) {
        productRepository.deleteById(productID);
    }
    public Product findByID(long productID) {
        return productRepository.findById(productID);
    }
    /*public List<Product> findByUserID(long userID) {
        return productRepository.findByUserID(userID);
    }*/
}
