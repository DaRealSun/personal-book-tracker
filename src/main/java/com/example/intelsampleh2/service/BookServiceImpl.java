package com.example.intelsampleh2.service;

import com.example.intelsampleh2.entity.Book;
import com.example.intelsampleh2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Retrieve all books
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Retrieve a book by ID
    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Add a new book
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Update an existing book
    @Override
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setCategory(updatedBook.getCategory());
            return bookRepository.save(existingBook);
        });
    }

    // Delete a book by ID
    @Override
    public boolean deleteBook(Long id) {
        return bookRepository.findById(id).map(book -> {
            bookRepository.delete(book);
            return true;
        }).orElse(false);
    }
}
