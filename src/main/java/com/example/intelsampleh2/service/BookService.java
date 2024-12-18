package com.example.intelsampleh2.service;

import com.example.intelsampleh2.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book addBook(Book book);
    Optional<Book> updateBook(Long id, Book updatedBook);
    boolean deleteBook(Long id);
}
