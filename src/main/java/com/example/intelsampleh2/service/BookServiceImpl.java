package com.example.intelsampleh2.service;

import com.example.intelsampleh2.entity.Book;
import com.example.intelsampleh2.entity.ReadingStatus;
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
        // If the incoming book’s status is null, default to TO_READ
        if (book.getStatus() == null) {
            book.setStatus(ReadingStatus.TO_READ);
        }
        return bookRepository.save(book);
    }

    // Update an existing book
    @Override
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setCategory(updatedBook.getCategory());
            // If you also want to allow updating status:
            existingBook.setStatus(updatedBook.getStatus());
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

    // New method to retrieve books by ReadingStatus
    @Override
    public List<Book> getBooksByStatus(ReadingStatus status) {
        return bookRepository.findByStatus(status);
    }
    public Optional<Book> updateBookStatus(Long id, ReadingStatus newStatus) {
        // 1. Find the existing book
        return bookRepository.findById(id).map(book -> {
            // 2. Update status
            book.setStatus(newStatus);
            // 3. Save changes
            return bookRepository.save(book);
        });
    }
}
