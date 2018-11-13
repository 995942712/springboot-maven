package com.ming.jdbc.jdbc.dao;

import com.ming.jdbc.jdbc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> fingAll() {
        return jdbcTemplate.query("select * from user", new Object[]{}, new BeanPropertyRowMapper(User.class));
    }

    public int addUser(User user){
        return this.jdbcTemplate.update("insert into user values(?,?)", user.getName(), user.getPassword());
    }

}