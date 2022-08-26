package com.spdb.controller;

import com.spdb.pojo.Machine;
import com.spdb.service.impl.MachineServiceImpl;
import com.spdb.vo.MachineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class MachineController {

    @Autowired
    MachineServiceImpl machineService;

    @RequestMapping("/machineInfo")
    public String machineInfo(Model model){
        List<MachineVo> machineVos = machineService.getAllMachines();
        model.addAttribute("machines", machineVos);
        return "/machine/list";
    }

    @RequestMapping("/toAddMachinePage")
    public String toAddPage(){
        return "/machine/add";
    }

    @RequestMapping("/addMachine")
    public String addMachine(Machine machine){
        machineService.addMachine(machine);
        return "redirect:/user/machineInfo";
    }

    @RequestMapping("/toUpdateMachinePage")
    public String toUpdatePage(Model model, @RequestParam("id") Long id){
        // 查出该机器的信息
        Machine machine = machineService.getMachineById(id);
        // 返回前端
        model.addAttribute("machine", machine);
        System.out.println(machine);
        return "/machine/update";
    }

    @RequestMapping("/updateMachine")
    public String updateMachine(Machine machine){
        System.out.println(machine);
        machineService.updateMachine(machine);
        return "redirect:/user/machineInfo";
    }

    @RequestMapping("/deleteMachine")
    public String deleteMachine(Long id){
        machineService.deleteMachine(id);
        return "redirect:/user/machineInfo";
    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(@RequestBody Long[] ids){
        System.out.println(Arrays.toString(ids));
        return "redirect:/user/machineInfo";
    }
}
