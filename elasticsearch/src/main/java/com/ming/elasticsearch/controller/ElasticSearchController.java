package com.ming.elasticsearch.controller;

import com.ming.elasticsearch.domain.Book;
import com.ming.elasticsearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;

@Controller
public class ElasticSearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private BookService bookService;

    public String list(@PageableDefault(page = 1, value = 10) Pageable pageable){
        //按标题进行搜索
        Iterable<Book> iterable = bookService.findByTitle("test", PageRequest.of(1, 10));
        return iterable.toString();
    }

}