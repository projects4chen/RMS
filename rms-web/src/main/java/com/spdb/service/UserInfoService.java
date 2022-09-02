package com.spdb.service;

import com.alibaba.fastjson.JSON;
import com.spdb.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.concurrent.TimeUnit;

@Service
public class UserInfoService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void retUserInfo(Model model){
        // 判断用户信息是否在redis中
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String userJson = redisTemplate.opsForValue().get("USER_" + username);
        if (userJson == null){
            // 不在，添加进去
            User user = userService.getUserByUsername(username);
            userJson = JSON.toJSONString(user);
            redisTemplate.opsForValue().set("USER_" + username, userJson, 1, TimeUnit.DAYS);
        } else {
            // 在，返回
            User user = JSON.parseObject(userJson, User.class);
            if (user != null){
                model.addAttribute("userId", user.getUserId());
                model.addAttribute("nickname", user.getNickname());
                if (user.getIdentity() == 1)
                    model.addAttribute("isAdmin", "0");
                else
                    model.addAttribute("isAdmin", "1");
            }
        }
    }

    public User getUser(){
        // 获取当前用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // 从redis中取出
        String userJson = redisTemplate.opsForValue().get("USER_" + username);
        // 还原为user
        return JSON.parseObject(userJson, User.class);
    }
}
