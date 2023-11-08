package com.example.demo.Interpretation;



import com.example.demo.User.UserRepository;
import com.example.demo.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class InterpretationController {
    private final InterpretationRepository repository;
    private final UserRepository userRepository;

    public InterpretationController(InterpretationRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping("/interpretation")
    public ResponseEntity<Interpretation> createInterpretationRequest(
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("option3") String option3,
            @RequestParam("email") String email
    ) {
        try {

            User user = userRepository.findByEmail(email); // Fetch the user by email
            if (user == null) {
                return ResponseEntity.badRequest().build();
            }

            Interpretation request = new Interpretation();

            request.setOption1(option1);
            request.setOption2(option2);
            request.setOption3(option3);
            request.setEmail(email);
            request.setType("Interpretation");
            request.setUser(user);
            request.setNoOfWords(0);


            Interpretation savedInterpretation = repository.save(request);

            return ResponseEntity.ok(savedInterpretation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/interpretation")
    public ResponseEntity<List<Interpretation>> getContentCreationByEmail(@RequestParam("email") String email) {
        List<Interpretation> content = repository.findByEmail(email);
        if (!content.isEmpty()) {
            return ResponseEntity.ok(content);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}