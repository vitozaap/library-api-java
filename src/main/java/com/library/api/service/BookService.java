package com.library.api.service;

import com.library.api.model.Book;
import com.library.api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getALl() {
        return repository.findAll();
    }

    public Book findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found: " + id));
    }

    public void create(Book book) {
        repository.save(book);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Book update(String id, Book book) {
        Book existing = findById(id);
        existing.setAuthor(book.getAuthor());
        existing.setName(book.getName());
        return repository.save(existing);
    }

}
