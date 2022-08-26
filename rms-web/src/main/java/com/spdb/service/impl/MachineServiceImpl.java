package com.spdb.service.impl;

import com.spdb.mapper.MachineMapper;
import com.spdb.mapper.UserMapper;
import com.spdb.pojo.Machine;
import com.spdb.service.MachineService;
import com.spdb.vo.MachineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    MachineMapper machineMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<MachineVo> getAllMachines() {
        List<Machine> machines = machineMapper.getAllMachines();
        List<MachineVo> machineVos = new ArrayList<>();
        for (Machine machine : machines) {
            MachineVo machineVo = new MachineVo();
            // 转换注册时间
            Long registerDate = machine.getRegisterDate();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(registerDate * 1000));
            machineVo.setRegisterDate(date);
            // 转换使用情况
            Long userId = machine.getUserId();
            if (userId == 0){
                machineVo.setUseInfo("未使用");
            } else {
                String userName = userMapper.getUserNameById(userId);
                machineVo.setUseInfo(userName);
            }
            // 其余内容直接复制
            machineVo.setMachineId(machine.getMachineId());
            machineVo.setIp(machine.getIp());
            machineVo.setName(machine.getName());
            machineVo.setSid(machine.getSid());
            machineVo.setConfig(machine.getConfig());
            machineVo.setEnv(machine.getEnv());
            // 放入容器
            machineVos.add(machineVo);
        }
        return machineVos;
    }

    @Override
    public void addMachine(Machine machine) {
        // 添加注册时间
        machine.setRegisterDate(new Date().getTime());
        // 设置用户id为0
        machine.setUserId(0L);
        // 添加
        machineMapper.addMachine(machine);
    }
}
