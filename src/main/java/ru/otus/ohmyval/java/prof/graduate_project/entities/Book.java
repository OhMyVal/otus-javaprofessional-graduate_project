package ru.otus.ohmyval.java.prof.graduate_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "books",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"author", "title"}))
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "available")
    public Boolean available;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_readers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public Book(Long id, String author, String title, Boolean available) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
