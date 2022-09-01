package com.spdb.controller;

import com.spdb.pojo.User;
import com.spdb.utils.UserThreadLocal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        Thread t = Thread.currentThread();
        System.out.println("t2: " + t.toString());
        User user = UserThreadLocal.get();
        System.out.println("test: " + user.toString());
        return user.toString();
    }
}
