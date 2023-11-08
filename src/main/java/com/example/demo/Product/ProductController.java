package com.example.demo.Product;

import com.example.demo.User.UserRepository;
import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    private final ProductRepository repository;
    private final UserRepository userRepository; // Inject UserRepository

    @Autowired
    public ProductController(ProductRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createTranslationRequest(
            @RequestParam("file") MultipartFile file,
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("email") String email
    ) {
        try {

            User user = userRepository.findByEmail(email); // Fetch the user by email
            if (user == null) {
                return ResponseEntity.badRequest().build();
            }

            Product request = new Product();
            request.setFileName(file.getOriginalFilename());
            request.setOption1(option1);
            request.setOption2(option2);
            request.setEmail(email);
            request.setType("Document Translation");
            request.setNoOfWords(0);
            request.setUser(user);

            Product savedProduct = repository.save(request);

            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getContentCreationByEmail(@RequestParam("email") String email) {



        User user = userRepository.findByEmail(email); // Fetch the user by email
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Product> content = repository.findByUserId(user.getId());
        if (!content.isEmpty()) {
            return ResponseEntity.ok(content);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
