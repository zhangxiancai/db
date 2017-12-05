package com.example.demo.dao;

import com.mongodb.*;

import java.util.List;

/**
 * Created by kawano on 2017/12/4 14:24.
 */
public class MongoDBDao {

    DBCollection coll;

    public MongoDBDao(){
        String address = System.getenv("MONGODB_ADDRESS");
        String port = System.getenv("MONGODB_PORT");
        String[] addresses = address.split(";");
        String[] ports = port.split(";");
        List<ServerAddress> addresses1 = null;
        for (int i = 0; i < addresses.length; i++) {
            addresses1.add(new ServerAddress(addresses[i], Integer.valueOf(ports[i])));
        }
        MongoClient client = new MongoClient(addresses1);
        DB db = client.getDB("name");
        coll = db.getCollection("name");

    }
    public void insert(String key,String value){
        //写入数据

        BasicDBObject object = new BasicDBObject();
        object.append( key,value);
        coll.insert(object);

    }

    public void getAll(){

        //读出数据
        DBCursor dbCursor = coll.find();
        while (dbCursor.hasNext()) {
            DBObject dbObject = dbCursor.next();
            System. out.println(dbObject.toString());
        }
    }

    public String get(){

        //读出数据
        DBCursor dbCursor = coll.find();
        return dbCursor.next().toString();

    }

}

