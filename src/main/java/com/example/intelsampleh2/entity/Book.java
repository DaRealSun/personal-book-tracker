package com.example.intelsampleh2.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "BOOK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    // Parameterized Constructor
    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "Category is mandatory")
    @Column(nullable = false)
    private String category;
}
