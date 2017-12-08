package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kawano on 2017/12/4 14:23.
 */
public class MysqlDao {

    public static List<Map<String,Object>>  getAllNews() {

        String sql="select * from news";

        List<Map<String,Object>> test= null;
        try {
            test = DBUtil.executeQuery(sql);
            return test;
        } catch (Exception e) {
            e.printStackTrace();
        }
          return null;

    }

    public static int createDatabase(){

        String sql ="create database test";
        try {
            return DBUtil.executeCreateDatabase(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }




    public static int createTable(){

        String sql = "CREATE TABLE user " +
                "(id INTEGER auto_increment not NULL, " +
                " name VARCHAR(255), " +
                "createdate datetime,"+
                " PRIMARY KEY ( id ))";
        try {
           return DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
          return 0;
    }

    public static int insertUser(String name){

        String sql = "insert into user (name,createdate) values(?,?)";
        try {
            return DBUtil.executePreparedUpdate(sql,name,new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
           return 0;
    }
    public static List<Map<String,Object>> getDate(){
        String sql="select * from user limit 10";
        try {
            return DBUtil.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
  return null;
    }
}
