package com.example.demo.util;

/**
 * Created by kawano on 2017/12/8 11:05.
 */
public class ConnectionDB {

    public static String address;
    public static String port;
    public static String username;
    public static String password;
    public ConnectionDB() {
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        ConnectionDB.address = address;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        ConnectionDB.port = port;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ConnectionDB.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConnectionDB.password = password;
    }

    public ConnectionDB(String address, String port, String username, String password) {

        this.address=address;
        this.port=port;
        this.username=username;
        this.password=password;


    }
}
