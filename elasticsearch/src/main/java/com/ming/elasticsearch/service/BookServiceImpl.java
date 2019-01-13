package com.ming.elasticsearch.service;

import com.ming.elasticsearch.dao.BookDao;
import com.ming.elasticsearch.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book save(Book book) {
        return this.bookDao.save(book);
    }

    @Override
    public void delete(Book book) {
        this.bookDao.delete(book);
    }

    @Override
    public Book findOne(String id) {
        return null;
    }

    @Override
    public Iterable<Book> findAll() {
        return this.bookDao.findAll();
    }

    @Override
    public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
        return this.bookDao.findByAuthor(author, pageRequest);
    }

    @Override
    public Page<Book> findByTitle(String title, PageRequest pageRequest) {
        return this.bookDao.findByTitle(title, pageRequest);
    }

}