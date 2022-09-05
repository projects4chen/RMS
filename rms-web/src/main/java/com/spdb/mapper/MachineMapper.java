package com.spdb.mapper;

import com.spdb.pojo.Machine;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineMapper {
    // 查询所有的机器
    List<Machine> getAllMachines();

    // 添加一个机器
    void addMachine(Machine machine);

    // 根据id查机器
    Machine getMachineById(Long id);

    // 根据id更新机器信息
    void updateMachine(Machine machine);

    // 根据id删除机器
    void deleteMachine(Long id);

    // 更新机器的使用者
    void updateMachineUser(Long machineId, Long userId);
}
