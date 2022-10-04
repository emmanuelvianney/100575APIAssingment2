package com.apps.bookfarm.service;


import com.apps.bookfarm.model.Book;

public interface BookService {

    public Iterable<Book> getBooks();
    public void addNewBook(Book book);
    public void removeBook(Long id);

}
