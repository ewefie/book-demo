package com.example.booksdemo.service;

import com.example.booksdemo.repository.PolishBookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PolishBookService {
    private final PolishBookRepository repository;

    public PolishBookService(PolishBookRepository repository) {
        this.repository = repository;
    }


}
