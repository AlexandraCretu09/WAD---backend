package com.example.demo.User;

import com.example.demo.Product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @Value("${some.key:0}")
    private int isAdmin;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public long getId() { return this.id; }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public int getAdmin(){return this.isAdmin;}

    public void setAdmin(int isAdmin){ this.isAdmin = isAdmin;}

}
