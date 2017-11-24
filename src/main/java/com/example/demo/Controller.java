package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kawano on 2017/11/23 9:11.
 */
@org.springframework.stereotype.Controller

public class Controller {

    @RequestMapping("/xx")
    @ResponseBody
    public String Helloworld(){

         return "hello world!";
    }
}
