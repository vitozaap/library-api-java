package com.library.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String author;
    private String description;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Comment> comments;

}
