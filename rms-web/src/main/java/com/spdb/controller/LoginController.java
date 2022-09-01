package com.spdb.controller;

import com.alibaba.fastjson.JSON;
import com.spdb.pojo.User;
import com.spdb.service.UserService;
import com.spdb.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.Detail;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/main")
    public String hello(){
        // 根据用户username获取用户对象，存入Redis
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        String userJson = JSON.toJSONString(user);
        redisTemplate.opsForValue().set("USER_" + username, userJson, 1, TimeUnit.DAYS);
        return "redirect:/machine/machineInfo";
    }

}
