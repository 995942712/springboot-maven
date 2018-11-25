package com.ming.cache.dao;

import com.ming.cache.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Cacheable("user")
    @Override
    public List<User> fingAll() {
        simulateSlowService();
        return jdbcTemplate.query("select * from user", new Object[]{}, new BeanPropertyRowMapper(User.class));
    }

    public int addUser(User user){
        return this.jdbcTemplate.update("insert into user(name, password) values(?,?)", user.getName(), user.getPassword());
    }

    private void simulateSlowService() {
        try {
            long time = 10000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}