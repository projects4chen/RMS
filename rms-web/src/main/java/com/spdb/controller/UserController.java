package com.spdb.controller;

import com.spdb.mapper.UserMapper;
import com.spdb.pojo.Ident;
import com.spdb.pojo.User;
import com.spdb.service.IdentService;
import com.spdb.service.UserService;
import com.spdb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    IdentService identService;

    @RequestMapping("/userInfo")
    public String getAllUser(Model model){
        List<UserVo> userVos = userService.getAllUsers();
        model.addAttribute("users", userVos);
        return "/user/list";
    }

    @RequestMapping("/toAddUserPage")
    public String toAddUserPage(Model model){
        // 获取身份类型
        Collection<Ident> idents = identService.getAllIdents();
        model.addAttribute("idents", idents);
        return "/user/add";
    }

    @RequestMapping("/addUser")
    public String addUser(User user){
//        System.out.println(user);
        userService.addUser(user);
        return "redirect:/user/userInfo";
    }

    @RequestMapping("/toUpdateUserPage")
    public String toUpdateUserPage(Model model, @RequestParam("id") Long id){
        // 获取身份类型
        Collection<Ident> idents = identService.getAllIdents();
        model.addAttribute("idents", idents);
        // 获取用户信息
        User user = userService.getUserById(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "/user/update";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/user/userInfo";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(Long id){
        userService.deleteUser(id);
        return "redirect:/user/userInfo";
    }
}