package com.example.demo.contentCreation;

import com.example.demo.Product.Product;
import com.example.demo.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ContentController {
    private final ContentRepository repository;
    private final UserRepository userRepository;

    public ContentController(ContentRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping("/contentCreation")
    public ResponseEntity<ContentCreation> createInterpretationRequest(
            @RequestParam("option1") String option1,
            @RequestParam("option2") String option2,
            @RequestParam("no of words") int words,
            @RequestParam("email") String email
    ) {
        try {

            User user = userRepository.findByEmail(email); // Fetch the user by email
            if (user == null) {
                return ResponseEntity.badRequest().build();
            }

            ContentCreation request = new ContentCreation();

            request.setOption1(option1);
            request.setOption2(option2);
            request.setNoOfWords(words);
            request.setEmail(email);
            request.setType("Content Creation");
            request.setUser(user);

            ContentCreation savedContent = repository.save(request);


            return ResponseEntity.ok(savedContent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/contentCreation")
    public ResponseEntity<List<ProductDto>> getContentCreationByEmail(@RequestParam("email") String email) {
        //List<ContentCreation> content = repository.findByEmail(email);

        User user = userRepository.findByEmail(email); // Fetch the user by email
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        List<ContentCreation> content = repository.findByUserId(user.getId());
        if (!content.isEmpty()) {
            List<ProductDto> contentDtoList = new ArrayList<>();

            for (ContentCreation creation : content) {
                // Create a ContentCreationDto from the ContentCreation data
                ProductDto contentDto = new ProductDto(creation.getEmail(), creation.getType());
                contentDtoList.add(contentDto);
            }

            /*for (ProductDto creationDto : contentDtoList) {
                System.out.println("ContentCreation Object: " + creationDto);
            }*/

            return ResponseEntity.ok(contentDtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



