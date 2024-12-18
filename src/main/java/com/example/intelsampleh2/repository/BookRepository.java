package com.example.intelsampleh2.repository;


import com.example.intelsampleh2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional query methods can be defined here if needed.
}
