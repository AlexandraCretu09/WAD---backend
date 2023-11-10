package com.example.demo.Product;

import com.example.demo.Interpretation.Interpretation;
import com.example.demo.User.UserRepository;
import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final UserRepository userRepository;
    private ProductsService productService;

    @Autowired
    public ProductController(ProductRepository repository, UserRepository userRepository, ProductsService productService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.productService = productService;
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
            request.setOption3(null);
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

    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        // Log products to the console
        for (Product product : products) {
            System.out.println(product.getType());
        }
        return products;
    }
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Map<String, String>> deleteProduct(@RequestParam("id") long id) {
        try {
            Product product = productService.findByID(id);

            if (product == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Product not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            } else {
                System.out.println("Product found: " + product.getFileName() + " " + product.getType());
            }

            productService.deleteProduct(id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Product deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error deleting product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    /*
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getContentCreationByUserID(@RequestParam("user_id") long user_id) {
        List<Product> content = repository.findByUserID(user_id);
        if (!content.isEmpty()) {
            return ResponseEntity.ok(content);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}

