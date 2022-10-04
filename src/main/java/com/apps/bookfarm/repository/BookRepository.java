package com.apps.bookfarm.repository;

import com.apps.bookfarm.model.Author;
import com.apps.bookfarm.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByTitle(String title);
    List<Book> findByPublisher(String publisher);
    List<Book> findByIsbn(int isbn);
    List<Book> findByLength(int length);
    List<Book> findBySubjects(String subjects);

    Optional<Book> findByAuthor(Author author);
}
