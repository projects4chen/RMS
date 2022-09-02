package com.spdb.controller;

import com.alibaba.fastjson.JSON;
import com.spdb.pojo.Machine;
import com.spdb.pojo.User;
import com.spdb.service.ApplicationService;
import com.spdb.service.UserInfoService;
import com.spdb.service.impl.MachineServiceImpl;
import com.spdb.vo.MachineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineServiceImpl machineService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping("/machineInfo")
    public String machineInfo(Model model){
        // 获取机器信息
        List<MachineVo> machineVos = machineService.getAllMachines();
        model.addAttribute("machines", machineVos);
        userInfoService.retUserInfo(model);
//        System.out.println("test: " + model.getAttribute("isAdmin"));
        return "/machine/list";
    }

    @RequestMapping("/toAddMachinePage")
    public String toAddPage(){
        return "/machine/add";
    }

    @RequestMapping("/addMachine")
    public String addMachine(Machine machine){
        machineService.addMachine(machine);
        return "redirect:/machine/machineInfo";
    }

    @RequestMapping("/toUpdateMachinePage")
    public String toUpdatePage(Model model, @RequestParam("id") Long id){
        // 查出该机器的信息
        Machine machine = machineService.getMachineById(id);
        // 返回前端
        model.addAttribute("machine", machine);
//        System.out.println(machine);
        return "/machine/update";
    }

    @RequestMapping("/updateMachine")
    public String updateMachine(Machine machine){
        System.out.println(machine);
        machineService.updateMachine(machine);
        return "redirect:/machine/machineInfo";
    }

    @RequestMapping("/deleteMachine")
    public String deleteMachine(Long id){
        machineService.deleteMachine(id);
        return "redirect:/machine/machineInfo";
    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(@RequestBody Long[] ids){
        System.out.println(Arrays.toString(ids));
        return "redirect:/machine/machineInfo";
    }


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
}
