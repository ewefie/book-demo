package com.example.booksdemo.service;

import com.example.booksdemo.exceptions.BookAlreadyExistsException;
import com.example.booksdemo.exceptions.BookNotFoundException;
import com.example.booksdemo.model.BookForm;
import com.example.booksdemo.model.PolishBook;
import com.example.booksdemo.model.PolishBooks;
import com.example.booksdemo.repository.PolishBookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PolishBookService {
    private final PolishBookRepository repository;

    public PolishBookService(PolishBookRepository repository) {
        this.repository = repository;
    }

    public PolishBook create(final BookForm form) {
        PolishBook bookToAdd = new PolishBook();
        bookToAdd.setAuthor(form.getAuthor());
        bookToAdd.setIsbn(form.getIsbn());
        bookToAdd.setTitle(form.getTitle());
        bookToAdd.setPagesNum(form.getPageNum());
        //        throwIfGivenBookExist(book);
        return repository.create(bookToAdd);
    }

//    private void throwIfGivenBookExist(PolishBook book) {
//        repository.findByAuthorAndTitle(book.getAuthor(),book.getTitle())
//                .ifPresent(pb -> {
//                    throw new BookAlreadyExistsException("Book with given author and title already exists");
//                });
//    }

    public PolishBooks findAllBooks() {
        return new PolishBooks(repository.getAll());
    }

    public PolishBook update(final PolishBook givenBook, final UUID id) {
        final PolishBook bookToUpdate = findExistingById(id);
        if (!bookToUpdate.getAuthor().equals(givenBook.getAuthor()) || !bookToUpdate.getTitle().equals(givenBook.getTitle())) {
//            throwIfGivenBookExist(givenBook);
        }
//        givenBook.setId(id);
        return repository.update(givenBook);
    }

    private PolishBook findExistingById(final UUID id) {
        return repository.getById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with given id doesn't exist"));
    }

    public PolishBook findById(final UUID id) {
        return findExistingById(id);
    }

    public List<PolishBook> findAll() {
        return repository.getAll();
    }

    public void deleteById(final UUID id) {
        findExistingById(id);
        repository.deleteById(id);
    }

    public List<PolishBook> findByTitle(final String title) {
        return repository.getByTitle(title);
    }

    public List<PolishBook> findByAuthor(final String author) {
        return repository.getByAuthor(author);
    }

    public List<PolishBook> findByAuthorAndTitle(final String author, final String title) {
        return repository.findByAuthorAndTitle(author, title);
    }
}
