package com.example.intelsampleh2.controller;

import com.example.intelsampleh2.entity.Book;
import com.example.intelsampleh2.entity.ReadingStatus;
import com.example.intelsampleh2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ============= GET: Retrieve all or by status =============
    /**
     * If "status" query param is provided, we return books of that status.
     * Otherwise, return all books.
     * Example: GET /book?status=TO_READ
     */
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) ReadingStatus status) {
        if (status != null) {
            return bookService.getBooksByStatus(status);
        } else {
            return bookService.getAllBooks();
        }
    }


    // ============= GET: Retrieve a book by ID =============
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============= POST: Add a new book =============
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        // new books can default to TO_READ or whatever logic you want
        return bookService.addBook(book);
    }

    // ============= PUT: Update an existing book (all fields) =============
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestBody Book updatedBook
    ) {
        return bookService.updateBook(id, updatedBook)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============= PUT: Only update status =============
    /**
     * Example usage: PUT /book/{id}/status?value=READING
     * or you could use a request body {"status": "READING"} if you prefer.
     */
    public ResponseEntity<Book> updateStatus(
            @PathVariable Long id,
            @RequestParam ReadingStatus value
    ) {
        return bookService.updateBookStatus(id, value)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // ============= DELETE: Delete a book by ID =============
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}

