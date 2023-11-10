package com.example.demo.Product;

import com.example.demo.User.User;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Product")
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String option1;
        private String option2;
        private String option3;
        private String fileName;
        private String email;
        private String type;
        private int words = 0;

        @ManyToOne
        @JoinColumn(name = "user_id") // This annotation specifies the column name for the foreign key in the products table.
        private User user;

        public String getFileName() {
            return this.fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
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
        public void setUser(User user) {this.user = user;}

        public String getType(){ return this.type; }
        public String getOption1(){ return this.option1; }
        public String getOption2(){ return this.option2; }
        public String getOption3(){ return this.option3; }
        public String getEmail(){return this.email;}
        public int getWords(){return this.words;}
        public User getUser(){return this.user;}
        public long getId(){return this.id;}
}

