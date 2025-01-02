package com.example.intelsampleh2.service;

import com.example.intelsampleh2.entity.Book;
import com.example.intelsampleh2.entity.ReadingStatus;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book addBook(Book book);
    Optional<Book> updateBook(Long id, Book updatedBook);
    boolean deleteBook(Long id);
    Optional<Book> updateBookStatus(Long id, ReadingStatus newStatus);
    // New method to retrieve books by ReadingStatus
    List<Book> getBooksByStatus(ReadingStatus status);
}
