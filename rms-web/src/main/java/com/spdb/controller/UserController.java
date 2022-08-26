package com.spdb.controller;

import com.spdb.service.UserService;
import com.spdb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/userInfo")
    public String getAllUser(Model model){
        List<UserVo> userVos = userService.getAllUsers();
        model.addAttribute("users", userVos);
        return "/user/list";
    }
}
