package com.example.booksdemo.repository;

import com.example.booksdemo.model.PolishBook;
import com.example.booksdemo.model.PolishBooks;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class PolishBookRepository {

    final EntityManager manager;

    public PolishBookRepository(EntityManager manager, EntityManager manager1) {
        this.manager = manager1;
    }

    public Optional<PolishBook> getById(final UUID id) {
        return Optional.ofNullable(manager.find(PolishBook.class, id));
    }

    @SuppressWarnings("unchecked")
    public List<PolishBook> getAll() {
        return manager.createQuery("select pb from polish_books pb").getResultList();
    }

    public PolishBook create(final PolishBook polishBook) {
        manager.persist(polishBook);
        return polishBook;
    }

    public PolishBook update(final PolishBook changedBook) {
        return manager.merge(changedBook);
    }

    public void deleteById(final UUID id) {
        manager.createQuery("delete from polish_books pb where  pb.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<PolishBook> getByAuthor(final String author) {
        return manager.createQuery("select pb from  polish_books  pb where  pb.author like :author")
                .setParameter("author", '%' + author + '%')
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<PolishBook> getByTitle(final String title) {
        return manager.createQuery("select pb from  polish_books  pb where  pb.title like :title")
                .setParameter("title", '%' + title + '%')
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<PolishBook> findByAuthorAndTitle(final String author, final String title) {
        return manager.createQuery("select pb from  polish_books  pb where  pb.title like :title and pb.author like :author")
                .setParameter("title", '%' + title + '%')
                .setParameter("author", '%' + author + '%')
                .getResultList();
    }
}
