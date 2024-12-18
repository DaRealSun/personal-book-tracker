package com.example.intelsampleh2.controller;

import com.example.intelsampleh2.entity.Book;
import com.example.intelsampleh2.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Book testBook;

    @BeforeEach
    public void setup() {
        // Clear repository before each test
        bookRepository.deleteAll();

        // Initialize a test book
        testBook = new Book("Test Title", "Test Author", "Test Category");
        testBook = bookRepository.save(testBook);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Title"));
    }

    @Test
    public void testGetBookById() throws Exception {
        mockMvc.perform(get("/book/{id}", testBook.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Title"));
    }

    @Test
    public void testCreateBook() throws Exception {
        Book newBook = new Book("New Title", "New Author", "New Category");

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("New Title"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book updatedBook = new Book("Updated Title", "Updated Author", "Updated Category");

        mockMvc.perform(put("/book/{id}", testBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));

        // Verify the update in the repository
        Optional<Book> optionalBook = bookRepository.findById(testBook.getId());
        assert(optionalBook.isPresent());
        assert(optionalBook.get().getTitle().equals("Updated Title"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/book/{id}", testBook.getId()))
                .andExpect(status().isNoContent());

        // Verify deletion in the repository
        Optional<Book> optionalBook = bookRepository.findById(testBook.getId());
        assert(optionalBook.isEmpty());
    }
}
