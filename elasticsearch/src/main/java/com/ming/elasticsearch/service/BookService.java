package com.ming.elasticsearch.service;

import com.ming.elasticsearch.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * BookService
 */
public interface BookService {

    Book save(Book book);
    void delete(Book book);
    Book findOne(String id);
    Iterable<Book> findAll();
    Page<Book> findByAuthor(String author, PageRequest pageRequest);
    Page<Book> findByTitle(String title, PageRequest pageRequest);

}