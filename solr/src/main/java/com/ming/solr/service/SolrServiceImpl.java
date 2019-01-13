package com.ming.solr.service;

import com.ming.solr.domain.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolrServiceImpl implements SolrService {

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("zs");
        user.setPassword("123456");
        list.add(user);

        user.setId(2);
        user.setName("ls");
        user.setPassword("123456");
        list.add(user);

        user.setId(3);
        user.setName("ww");
        user.setPassword("123456");
        list.add(user);
        return list;
    }

}