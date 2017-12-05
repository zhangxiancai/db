package com.example.demo;

import com.example.demo.dao.MongoDBDao;
import com.example.demo.dao.MysqlDao;
import com.example.demo.dao.RedisDao;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by kawano on 2017/11/23 9:11.
 */
@org.springframework.stereotype.Controller

public class Controller {

    RedisDao redisDao=new RedisDao();
    MongoDBDao mongoDBDao=new MongoDBDao();
    @RequestMapping("/xx")
    @ResponseBody
    public String Helloworld(){

         return "hello world!";
    }


    @RequestMapping("/mysql")
    @ResponseBody
    public List<Map<String,Object>> getMysql(){

        MysqlDao.createTable();
        MysqlDao.insertNews();
        List<Map<String,Object>> news= MysqlDao.getAllNews();


        return news;
    }


    @RequestMapping("/redis")
    @ResponseBody
    public String getRedis(){

        redisDao.set("name","redis");
        return redisDao.get("name");

    }

    @RequestMapping("/mongodb")
    @ResponseBody
    public String getMongodb(){

        mongoDBDao.insert("name","mongodb");
        return redisDao.get("name");

    }



}
