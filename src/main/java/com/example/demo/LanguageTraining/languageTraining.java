package com.example.demo.LanguageTraining;

import com.example.demo.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class languageTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String option1;
    private String option2;
    private String email;
    private String type;
    private int words;
    @ManyToOne
    @JoinColumn(name = "user_id") // This annotation specifies the column name for the foreign key in the products table.
    private User user;
    public void setOption1(String option) {
        this.option1 = option;
    }
    public void setOption2(String option) {
        this.option2 = option;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setType(String documentTranslation) {
        this.type = documentTranslation;
    }

    public void setUser(User user) {this.user = user;}
    public void setNoOfWords(int words) {
        this.words = words;
    }
}
