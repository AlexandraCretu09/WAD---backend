package com.example.demo.LanguageTraining;

import com.example.demo.User.UserRepository;
import com.example.demo.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class languageController {
    private final languageRepository repository;
    private final UserRepository userRepository;

    public languageController(languageRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }
    @PostMapping("/languageTraining")
    public ResponseEntity<languageTraining> createInterpretationRequest(
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("email") String email
    ) {
        try {

            User user = userRepository.findByEmail(email); // Fetch the user by email
            if (user == null) {
                return ResponseEntity.badRequest().build();
            }

            languageTraining request = new languageTraining();

            request.setOption1(option1);
            request.setOption2(option2);
            request.setEmail(email);
            request.setType("Language Training");
            request.setUser(user);
            request.setNoOfWords(0);

            languageTraining savedLanguageTraining = repository.save(request);

            return ResponseEntity.ok(savedLanguageTraining);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/languageTraining")
    public ResponseEntity<List<languageTraining>> getContentCreationByEmail(@RequestParam("email") String email) {
        List<languageTraining> content = repository.findByEmail(email);
        if (!content.isEmpty()) {
            return ResponseEntity.ok(content);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
