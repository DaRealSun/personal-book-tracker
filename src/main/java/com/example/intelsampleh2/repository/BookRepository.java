package com.example.intelsampleh2.repository;

import com.example.intelsampleh2.entity.Book;
import com.example.intelsampleh2.entity.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(ReadingStatus status);
}
