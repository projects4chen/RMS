package com.spdb.service;

import com.spdb.pojo.Machine;
import com.spdb.vo.MachineVo;

import java.util.List;

public interface MachineService {

    /**
     * 获取所有机器的信息
     * @return
     */
    List<MachineVo> getAllMachines();

    /**
     * 添加机器信息
     * @param machine
     */
    void addMachine(Machine machine);

    /**
     * 根据id查机器的信息
     * @param id
     * @return
     */
    Machine getMachineById(Long id);

    /**
     * 更新机器信息
     * @param machine
     */
    void updateMachine(Machine machine);
}
