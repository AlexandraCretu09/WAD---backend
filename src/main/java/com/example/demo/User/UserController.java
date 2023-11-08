package com.example.demo.User;

import com.example.demo.Product.Product;
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
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity <Map<String, Object>> signup(@RequestParam("name") String name,
                                                       @RequestParam("email") String email,
                                                       @RequestParam("password") String password
    ) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);


        try {
            //System.out.println(user.getName() +  " " + user.getEmail() + " " + user.getPassword() + " " + user.getAdmin() +"\n\n");

            userService.createUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User created successfully");
            response.put("user", user);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error creating user in Spring: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<Map<String, String>> deleteAccount(@RequestParam("email") String email) {
        System.out.println("Spring email: " + email);

        try {

            User user = userService.findByEmail(email);
            if (user == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            else{
                System.out.println("User found: " + user.getName() + " " + user.getEmail() + " " + user.getPassword());
            }



            userService.deleteUser(user.getId());


            Map<String, String> response = new HashMap<>();
            response.put("message", "User account deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error deleting user account in Spring: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();

        for(User user : users)
            System.out.println(user);
        return users;
    }
    @PutMapping("/updateAdminStatus")
    public ResponseEntity<String> updateAdminStatus(@RequestParam Long id, @RequestParam int admin) {
        User updatedUser = userService.updateUserAdminStatus(id, admin);

        if (updatedUser != null) {
            return ResponseEntity.ok("Admin status updated successfully");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }

    }
}