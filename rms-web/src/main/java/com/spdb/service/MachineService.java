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

    /**
     * 根据id删除机器
     * @param id
     */
    void deleteMachine(Long id);

    /**
     * 当前用户是否已经申请该机器
     * @param machineId
     * @param userId
     * @return
     */
    boolean hasApplied(Long machineId, Long userId);

    /**
     * 释放机器
     * @param machineId
     */
    void releaseMachine(Long machineId);
}
