package com.example.demo.Interpretation;

import com.example.demo.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Interpretation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String option1;
    private String option2;
    private String option3;
    private String email;
    private String type;

    private int words = 0;
    @ManyToOne
    @JoinColumn(name = "user_id") // This annotation specifies the column name for the foreign key in the products table.
    private User user;

    public void setOption1(String option) {
        this.option1 = option;
    }
    public void setOption2(String option) {
        this.option2 = option;
    }
    public void setOption3(String option) {
        this.option3 = option;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setType(String documentTranslation) {
        this.type = documentTranslation;
    }
    public void setNoOfWords(int words) {
        this.words = words;
    }
    public void setUser(User user) { this.user = user;}


}
