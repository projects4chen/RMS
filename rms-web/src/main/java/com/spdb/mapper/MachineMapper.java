package com.spdb.mapper;

import com.spdb.pojo.Machine;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineMapper {
    // 查询所有的机器
    List<Machine> getAllMachines();
}
