package com.example.booksdemo.playground;

import com.example.booksdemo.model.PolishBook;
import com.example.booksdemo.repository.PolishBookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("h2")
@Component
public class BookRunner implements CommandLineRunner {
    private final PolishBookRepository repository;

    public BookRunner(PolishBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        PolishBook book1 = new PolishBook();
        book1.setPagesNum(55);
        book1.setIsbn("mdkj234enwd");
        book1.setAuthor("Lolek Lolkowski");
        book1.setTitle("Lolkowanie");
        PolishBook book2 = new PolishBook();
        book2.setTitle("Kotki");
        book2.setAuthor("Pan Kotek");
        book2.setIsbn("nhdqieujbd");
        book2.setPagesNum(12);
        PolishBook book3 = new PolishBook();
        book3.setPagesNum(233);
        book3.setIsbn("klsou893jd");
        book3.setTitle("cos nudnego");
        book3.setAuthor("Pan Nudziarz");
        repository.create(book1);
        repository.create(book2);
        repository.create(book3);
        System.out.println("lolki:" + book1.toString() + book2.toString() + book3.toString());
    }
}
