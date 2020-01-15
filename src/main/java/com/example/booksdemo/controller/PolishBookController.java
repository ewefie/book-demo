package com.example.booksdemo.controller;

import com.example.booksdemo.model.PolishBook;
import com.example.booksdemo.model.PolishBooks;
import com.example.booksdemo.service.PolishBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/polish-books")
public class PolishBookController {
    private final PolishBookService service;

    public PolishBookController(PolishBookService service) {
        this.service = service;
    }

    @GetMapping
    public PolishBooks findAll() {
        return new PolishBooks(service.findAll());
    }

    @GetMapping("/{id}")
    public PolishBook findById(@PathVariable final UUID id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PolishBook create(@Valid @RequestBody final PolishBook parkingLot) {
        return service.create(parkingLot);
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
