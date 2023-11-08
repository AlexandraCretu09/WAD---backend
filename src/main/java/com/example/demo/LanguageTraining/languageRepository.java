package com.example.demo.LanguageTraining;

import com.example.demo.Interpretation.Interpretation;
import com.example.demo.contentCreation.ContentCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface languageRepository extends JpaRepository<languageTraining, Long> {
    List<languageTraining> findByEmail(String email);
}
