package com.spdb.controller;

import com.spdb.mapper.UserMapper;
import com.spdb.pojo.Ident;
import com.spdb.pojo.User;
import com.spdb.service.IdentService;
import com.spdb.service.UserInfoService;
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
    private UserService userService;

    @Autowired
    private IdentService identService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/userInfo")
    public String getAllUser(Model model){
        userInfoService.retUserInfo(model);
        List<UserVo> userVos = userService.getAllUsers();
        model.addAttribute("users", userVos);
        return "/user/list";
    }

    @RequestMapping("/toAddUserPage")
    public String toAddUserPage(Model model){
        userInfoService.retUserInfo(model);
        // 获取身份类型
        Collection<Ident> idents = identService.getAllIdents();
        model.addAttribute("idents", idents);
        return "/user/add";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model, User user){
        userInfoService.retUserInfo(model);
//        System.out.println(user);
        if (userService.addUser(user)){
            return "redirect:/user/userInfo";
        } else {
            model.addAttribute("msg", "账号已存在");
            Collection<Ident> idents = identService.getAllIdents();
            model.addAttribute("idents", idents);
            return "/user/add";
        }
    }

    @RequestMapping("/toUpdateUserPage")
    public String toUpdateUserPage(Model model, @RequestParam("id") Long id){
        userInfoService.retUserInfo(model);
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
