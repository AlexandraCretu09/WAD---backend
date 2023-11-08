
package com.example.demo.User;

//package com.example.myapp.repository;
import com.example.demo.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    //List<User> findByUserId(Long id);

}