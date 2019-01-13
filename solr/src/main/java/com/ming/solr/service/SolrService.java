package com.ming.solr.service;

import com.ming.solr.domain.User;
import java.util.List;

public interface SolrService {

    List<User> findAll();

}