package com.spdb.controller;

import com.spdb.service.ApplicationService;
import com.spdb.service.UserInfoService;
import com.spdb.vo.ApplicationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping("/myApplications")
    public String myApplications(Model model, @RequestParam("userId") Long userId){
        userInfoService.retUserInfo(model);
        // 获取自己的申请
        List<ApplicationVo> applicationVos = applicationService.getApplicationsByUserId(userId);
        model.addAttribute("apps", applicationVos);
        return "/application/myApplications";
    }
}
