package com.spdb.service.impl;

import com.spdb.mapper.MachineMapper;
import com.spdb.pojo.Machine;
import com.spdb.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    MachineMapper machineMapper;

    @Override
    public List<Machine> getAllMachines() {
        return machineMapper.getAllMachines();
    }
}
