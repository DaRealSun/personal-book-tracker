package com.example.intelsampleh2.controller;

import com.example.intelsampleh2.entity.Book;
import com.example.intelsampleh2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
//Return all thhingelate to book
//Each Controller in the future have diffrent mapping
//LibraryController /library -> access and return anything relate to library
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET: Retrieve all books
    //localhost:8080/ -> json book
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET: Retrieve a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Add a new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // PUT: Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
