package com.ming.jpa.user.dao;

import com.ming.jpa.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {



}