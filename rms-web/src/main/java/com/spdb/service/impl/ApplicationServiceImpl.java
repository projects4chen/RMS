package com.spdb.service.impl;

import com.spdb.mapper.ApplicationMapper;
import com.spdb.mapper.MachineMapper;
import com.spdb.pojo.Application;
import com.spdb.pojo.Machine;
import com.spdb.service.ApplicationService;
import com.spdb.vo.ApplicationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private MachineMapper machineMapper;

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
        for (ApplicationVo applicationVo : applicationVos) {
            long date = Long.parseLong(applicationVo.getAppDate());
            applicationVo.setAppDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date)));
        }
        return applicationVos;
    }

    @Override
    public Application getAppById(Long appId) {
        return applicationMapper.getAppById(appId);
    }

    @Override
    public void updateApp(Long appId, String appBody) {
        Application application = applicationMapper.getAppById(appId);
        application.setAppBody(appBody);
        applicationMapper.updateApp(application);
    }

    @Override
    public void revokeApp(Long appId) {
        Application application = applicationMapper.getAppById(appId);
        application.setState("已撤销");
        applicationMapper.updateApp(application);
    }

    @Override
    public List<ApplicationVo> getAllPendingApps() {
        // 查出所有待审批的申请
        List<ApplicationVo> applicationVos = applicationMapper.getAllPendingApps();
        // 时间替换
        for (ApplicationVo applicationVo : applicationVos) {
            long date = Long.parseLong(applicationVo.getAppDate());
            applicationVo.setAppDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date)));
        }
        return applicationVos;
    }

    @Override
    public void approvalApp(Long appId, int agree) {
        Application application = applicationMapper.getAppById(appId);
        // 同意申请
        if (agree == 1){
            // 1. 更新申请工单的状态
            application.setState("通过");
            applicationMapper.updateApp(application);
            // 2. 更新机器的使用情况（使用者id）
            machineMapper.updateMachineUser(application.getMachineId(), application.getApplicantId());
        } else {    // 拒绝
            // 更新申请工单状态
            application.setState("拒绝");
            applicationMapper.updateApp(application);
        }

    }

}
