package com.spdb.service.impl;

import com.spdb.mapper.MachineMapper;
import com.spdb.mapper.UserMapper;
import com.spdb.pojo.Machine;
import com.spdb.pojo.User;
import com.spdb.service.ApplicationService;
import com.spdb.service.MachineService;
import com.spdb.service.UserInfoService;
import com.spdb.vo.MachineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ApplicationService applicationService;

    @Override
    public List<MachineVo> getAllMachines() {
        List<Machine> machines = machineMapper.getAllMachines();
        List<MachineVo> machineVos = new ArrayList<>();
        for (Machine machine : machines) {
            MachineVo machineVo = new MachineVo();
            // 转换注册时间
            Long registerDate = machine.getRegisterDate();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(registerDate));
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
            machineVo.setUserId(machine.getUserId());
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

    @Override
    public Machine getMachineById(Long id) {
        return machineMapper.getMachineById(id);
    }

    @Override
    public void updateMachine(Machine machine) {
        machineMapper.updateMachine(machine);
    }

    @Override
    public void deleteMachine(Long id) {
        machineMapper.deleteMachine(id);
    }

    @Override
    public boolean hasApplied(Long machineId, Long userId) {
        // 获取该机器的使用者id
        Long useId = machineMapper.getMachineById(machineId).getUserId();
        // 判断
        return 0L != useId;
    }

    @Override
    public void releaseMachine(Long machineId) {
        // 检验是否是使用者本人或管理员
        Machine machine = machineMapper.getMachineById(machineId);
        User user = userInfoService.getUser();
        if (Objects.equals(machine.getUserId(), user.getUserId()) || user.getIdentity() != 1){
            // 释放
            machine.setUserId(0L);
            machineMapper.updateMachineUser(machineId, 0L);
        }
    }
}
