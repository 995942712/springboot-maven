package com.ming.mybatis.user.dao;

import com.ming.mybatis.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
public interface UserDao {

    //@Select("select * from user")
    List<User> fingAll();

    //@Insert("insert into user(name, password) values(#{name}, #{password})")
    int addUser(User user);

    //@Update("update user set name=#{name}, password=#{password} where id=#{id}")
    int updateUser(User user);

    //@Delete("delete from user where id=#{id}")
    int deleteUser(@Param("id") int id);

}