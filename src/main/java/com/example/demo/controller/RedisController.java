package com.example.demo.controller;

import com.example.demo.dao.RedisDao;
import com.example.demo.util.ConnectionDB;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kawano on 2017/12/8 18:20.
 */
@Controller
public class RedisController {

    @RequestMapping("/Redis")
    public String redis(){
       return "Redis";

    }


    @RequestMapping("/Redis_connection")
    public String connection(HttpServletRequest request){
        RedisDao redisDao=new RedisDao();
        ConnectionDB connectionDB=new ConnectionDB();
        connectionDB.setAddress(request.getParameter("address"));
        connectionDB.setPort(request.getParameter("port"));

        return "Redis";

    }


    @RequestMapping("/set")
    public String set(HttpServletRequest request, Model model){
        RedisDao redisDao=new RedisDao();
        redisDao.set(request.getParameter("key"),request.getParameter("value"));

        return "Redis";

    }

    @RequestMapping("/get")
    public String get(HttpServletRequest request, Model model){
        RedisDao redisDao=new RedisDao();

        model.addAttribute("key",redisDao.get(request.getParameter("key")));
        return "Redis";

    }

}
