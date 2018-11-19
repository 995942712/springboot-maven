package com.ming.redis.controller;

import com.ming.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisController {

    @Autowired
    private RedisDao redisDao;

    @ResponseBody
    @RequestMapping("/redis")
    public String getRedis(){
        //this.redisDao.setKey("password", "123456");
        //String value = this.redisDao.getKey("name");
        return this.redisDao.getKey("name") + "=" + this.redisDao.getKey("password");
    }

}