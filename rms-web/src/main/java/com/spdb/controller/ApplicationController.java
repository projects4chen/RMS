package com.spdb.controller;

import com.spdb.pojo.Machine;
import com.spdb.service.ApplicationService;
import com.spdb.service.MachineService;
import com.spdb.service.UserInfoService;
import com.spdb.vo.ApplicationVo;
import com.spdb.vo.MachineVo;
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
    private MachineService machineService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/toApplyPage")
    public String toApplyPage(Model model, @RequestParam("machineId") Long machineId, @RequestParam("userId") Long userId){
        userInfoService.retUserInfo(model);
        boolean check = false;
        String msg = "";
        // 检测是否重复申请
        if (machineService.hasApplied(machineId, userId)) {
            check = true;
            msg = "该机器已被申请";
            // 检测是否已申请
        } else if (applicationService.isBeingApplied(machineId, userId)) {
            check = true;
            msg = "您的申请待审批";
        }
        if (check) {
            model.addAttribute("msg", msg);
            // 获取机器信息
            List<MachineVo> machineVos = machineService.getAllMachines();
            model.addAttribute("machines", machineVos);
            userInfoService.retUserInfo(model);
            return "/machine/list";
        } else {
            // 满足申请要求，获取该机器信息，返回申请页面
            Machine machine = machineService.getMachineById(machineId);
            model.addAttribute("machine", machine);
        }
        return "/machine/application";
    }

    @RequestMapping("/appMachine")
    public String appMachine(@RequestParam("machineId") Long machineId, @RequestParam("userId") Long userId,
                             @RequestParam("reason") String reason){
        System.out.println("test:" + machineId + ", " + userId + ", " + reason);
        applicationService.addApplication(machineId, userId, reason);
        return "redirect:/machine/machineInfo";
    }

    @RequestMapping("/myApplications")
    public String myApplications(Model model, @RequestParam("userId") Long userId){
        userInfoService.retUserInfo(model);
        // 获取自己的申请
        List<ApplicationVo> applicationVos = applicationService.getApplicationsByUserId(userId);
        model.addAttribute("apps", applicationVos);
        return "/application/myApplications";
    }
}
