package com.example.booksdemo.controller;

import com.example.booksdemo.model.BookForm;
import com.example.booksdemo.model.PolishBook;
import com.example.booksdemo.service.PolishBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@RequestMapping("api/books")
@Controller
public class ViewBookController {
    private final PolishBookService service;

    public ViewBookController(PolishBookService service) {
        this.service = service;
    }

    @GetMapping(path = "/")
    public String getIndexPage(final ModelMap map) {
        return "index";
    }

    @GetMapping(path = "/list")
    public String getBookList(@RequestParam(name = "title", required = false) final String title, @RequestParam(name = "author", required = false) final String author, final ModelMap modelMap) {
        List<PolishBook> booksToDisplay;
        if ((isNull(title) && isNull(author)) || (title.equals("") && author.equals(""))) {
            booksToDisplay = service.findAll();
        } else if (isNull(author) || author.equals("")) {
            booksToDisplay = service.findByTitle(title);
        } else if (title.equals("")) {
            booksToDisplay = service.findByAuthor(author);
        } else {
            booksToDisplay = service.findByAuthorAndTitle(author, title);
        }
        modelMap.addAttribute("books", booksToDisplay);
        modelMap.addAttribute("author", author);
        modelMap.addAttribute("title", title);
        return "book-list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable final String id, final ModelMap modelMap) {
        modelMap.addAttribute("books", List.of(service.findById(UUID.fromString(id))));
        return "book-list";
    }

    @GetMapping(path = "/add")
    public String getForm(final ModelMap modelMap) {
        modelMap.addAttribute("bookForm", new BookForm());
        modelMap.addAttribute("btnText", "Add a book");
        return "book-form";
    }

//    @PostMapping(path = "/add", consumes = "application/x-www-form-urlencoded")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String create(@Valid @RequestBody final PolishBook polishBook) {
//        service.create(polishBook);
//        return "redirect:/api/books/list";
//    }

    @PostMapping(path = "/add")
    public String createOrUpdate(@ModelAttribute final BookForm form) {
        service.create(form);
        return "redirect:/api/books/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(final ModelMap map, @PathVariable(name = "id") final String id) {
        final PolishBook book = service.findById(UUID.fromString(id));
        map.addAttribute("book", book);
        return "book-form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(name = "id") final String id) {
        service.deleteById(UUID.fromString(id));
        return "redirect:/api/books/list";
    }


//    @PutMapping("/{id}")
//    public void update(@PathVariable final UUID id, @Valid @RequestBody final PolishBook book) {
//        service.update(book, id);
//    }

}

