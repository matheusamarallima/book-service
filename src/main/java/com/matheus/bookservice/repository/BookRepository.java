package com.matheus.bookservice.repository;

import com.matheus.bookservice.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
