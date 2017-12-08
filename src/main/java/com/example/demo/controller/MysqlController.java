package com.example.demo.controller;

import com.example.demo.dao.MongoDBDao;
import com.example.demo.dao.MysqlDao;
import com.example.demo.dao.RedisDao;
import com.example.demo.util.ConnectionDB;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kawano on 2017/11/23 9:11.
 */
@org.springframework.stereotype.Controller

public class MysqlController {


    @RequestMapping("/")

    public String table(){

        return "Main";
    }


    @RequestMapping("/xx")
    @ResponseBody
    public String Helloworld(){

         return "hello world!";
    }


    @RequestMapping("/Mysql_connection")
    public String connection(HttpServletRequest request,Model model){

        ConnectionDB connectionDB =new ConnectionDB
                (request.getParameter("address"),request.getParameter("port"),
                        request.getParameter("username"),request.getParameter("password"));
        model.addAttribute(connectionDB);
        model.addAttribute("message1","已将连接信息保存在服务器^.^");
        return "Mysql";
    }

    @RequestMapping("/createDatabase")
    public String createDatabase(Model model){

        int temp=MysqlDao.createDatabase();
        if (temp==1){
            model.addAttribute("message2","已连接数据库（未指定数据库名），已创建数据库（test），已关闭连接^.^");}
        else {
            model.addAttribute("message2","创建数据库（test）失败");
        }
        return "Mysql";
    }

    @RequestMapping("/createTable")
    public String getMysql(Model model){

        int temp=MysqlDao.createTable();
        if (temp==0){
        model.addAttribute("message3","已连接数据库（test），已创建表user（id（自增） ，name），已关闭连接^.^");}
        else {
            model.addAttribute("message3","创建表user（id（自增） ，name）失败");
        }
        return "Mysql";
    }


    @RequestMapping("/insertData")
    public String insertData(Model model,HttpServletRequest request){

        int temp=MysqlDao.insertUser(request.getParameter("name"));
        if (temp==1){
            model.addAttribute("message4","已连接数据库（test），已插入数据，已关闭连接^.^");}
        else {
            model.addAttribute("message4","插入数据失败");
        }
        return "Mysql";
    }

    @RequestMapping("/showData")
    public String showData(Model model){

        model.addAttribute("users",MysqlDao.getDate());

        return "Mysql";
    }


    @RequestMapping("/Mysql")
    public String mysql(){
        return "Mysql";
    }





















    @RequestMapping("/mongodb")
    @ResponseBody
    public String getMongodb(){
        MongoDBDao mongoDBDao=new MongoDBDao();
        mongoDBDao.insert("name","mongodb");
        return mongoDBDao.get();

    }



}
