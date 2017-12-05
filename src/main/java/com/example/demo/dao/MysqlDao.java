package com.example.demo.dao;

import java.util.ArrayList;
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

    public static void createTable(){

        String sql = "CREATE TABLE news " +
                "(id INTEGER not NULL, " +
                " title VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        try {
            DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insertNews(){

        String sql = "insert into news () values(1,\"java\")";
        try {
            DBUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
