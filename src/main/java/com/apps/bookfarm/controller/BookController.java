package com.apps.bookfarm.controller;

import com.apps.bookfarm.model.Book;
import com.apps.bookfarm.repository.BookRepository;
import com.apps.bookfarm.serviceimpl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookRepository bookRepository, BookServiceImpl bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping("/books/{id}")
    EntityModel<Book> oneBook (@PathVariable Long id){
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).oneBook(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).allBooks()).withRel("Books"));
    }

    @GetMapping("/books")
    CollectionModel<EntityModel<Book>> allBooks (){
        List<EntityModel<Book>> books = bookRepository.findAll().stream()
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookController.class).oneBook(book.getBookId())).withSelfRel(),
                        linkTo(methodOn(BookController.class).allBooks()).withRel("Books"))).collect(Collectors.toList());

        return CollectionModel.of(books,
                linkTo(methodOn(BookController.class).allBooks()).withSelfRel());
    }
}
