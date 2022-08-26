package com.spdb.controller;

import com.spdb.pojo.Machine;
import com.spdb.service.impl.MachineServiceImpl;
import com.spdb.vo.MachineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class MachineController {

    @Autowired
    MachineServiceImpl machineService;

    @GetMapping("/machineInfo")
    public String machineInfo(Model model){
        List<MachineVo> machineVos = machineService.getAllMachines();
        model.addAttribute("machines", machineVos);
        return "/machine/list";
    }

    @PostMapping("/toAddPage")
    public String addMachine(){
        return "/machine/add";
    }
}
