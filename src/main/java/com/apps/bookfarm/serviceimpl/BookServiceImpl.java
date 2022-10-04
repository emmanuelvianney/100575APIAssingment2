package com.apps.bookfarm.serviceimpl;

import com.apps.bookfarm.model.Book;
import com.apps.bookfarm.repository.BookRepository;
import com.apps.bookfarm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addNewBook(Book book) {

        List<Book> optionalBook;
        optionalBook = bookRepository.findByTitle(book.getTitle());
        optionalBook = bookRepository.findByPublisher(book.getPublisher());
        optionalBook = bookRepository.findByIsbn(book.getIsbn());
        optionalBook = bookRepository.findByLength(book.getLength());
        optionalBook = bookRepository.findBySubjects(book.getSubjects());
        if(!optionalBook.isEmpty()){
            throw new IllegalStateException("Book already exists");
        }else {
            bookRepository.save(book);
        }
    }


    @Override
    public void removeBook(Long id) {

    }


}
