package com.example.demo.contentCreation;

public class ProductDto {
    private String email;
    private String type;

    // Getters and setters for email and type

    public ProductDto(String email, String type) {
        this.email = email;
        this.type = type;
    }
    public String toString() {
        return "ProductDTO{email='" + email + "', type='" + type + "}"; // Include other relevant fields
    }
}
