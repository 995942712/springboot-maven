package com.ming.elasticsearch.dao;

import com.ming.elasticsearch.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * BookDao
 */
public interface BookDao extends ElasticsearchRepository<Book, String> {

    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book> findByTitle(String title, Pageable pageable);

}