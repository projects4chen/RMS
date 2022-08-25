package com.spdb.service;

import com.spdb.pojo.Machine;

import java.util.List;

public interface MachineService {

    /**
     * 获取所有机器的信息
     * @return
     */
    List<Machine> getAllMachines();
}
