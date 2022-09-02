package com.spdb.service.impl;

import com.spdb.mapper.ApplicationMapper;
import com.spdb.pojo.Application;
import com.spdb.service.ApplicationService;
import com.spdb.vo.ApplicationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public void addApplication(Long machineId, Long userId, String reason) {
        // 当前时间
        Long date = new Date().getTime();
        // 赋对象
        Application application = new Application();
        application.setAppDate(date);
        application.setApplicantId(userId);
        application.setMachineId(machineId);
        application.setState("待审批");
        application.setAppBody(reason);
        application.setRepBody("");
        // 添加
        applicationMapper.addApplication(application);
    }

    @Override
    public boolean isBeingApplied(Long machineId, Long userId) {
        // 获取最新的申请状态
        String state = applicationMapper.getAppState(machineId, userId);
        return state != null && state.equals("待审批");
    }

    @Override
    public List<ApplicationVo> getApplicationsByUserId(Long userId) {
        // 获取所有的申请
        List<ApplicationVo> applicationVos = applicationMapper.getAppsByUserId(userId);
        return applicationVos;
    }
}
