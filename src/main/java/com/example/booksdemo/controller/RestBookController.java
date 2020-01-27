package com.example.booksdemo.controller;

import com.example.booksdemo.model.BookForm;
import com.example.booksdemo.model.PolishBook;
import com.example.booksdemo.model.PolishBooks;
import com.example.booksdemo.service.PolishBookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

import javax.validation.Valid;
import java.util.UUID;

//@RestController
//@RequestMapping("api/books")
public class RestBookController {
    private final PolishBookService service;

    public RestBookController(PolishBookService service) {
        this.service = service;
    }

    @GetMapping
    public PolishBooks findAll(@RequestParam(name = "title", required = false) final String title, @RequestParam(name = "author", required = false) final String author) {
        if (isNull(title) && isNull(author)) {
            return new PolishBooks(service.findAll());
        }
        if (isNull(author)) {
            return new PolishBooks(service.findByTitle(title));
        }
        return new PolishBooks(service.findByAuthor(author));
    }

    @GetMapping("/{id}")
    public PolishBook findById(@PathVariable final UUID id) {
        return service.findById(id);
    }

//    @GetMapping("/{author}")
//    public PolishBooks findByAuthor(@PathVariable final String author) {
//        return new PolishBooks(service.findByAuthor(author));
//    }
//
//    @GetMapping("/{title}")
//    public PolishBooks findByTitle(@PathVariable final String title) {
//        return new PolishBooks(service.findByTitle(title));
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PolishBook create(@RequestBody final BookForm bookForm) {
        return service.create(bookForm);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable final UUID id, @Valid @RequestBody final PolishBook book) {
        service.update(book, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final UUID id) {
        service.deleteById(id);
    }
}
