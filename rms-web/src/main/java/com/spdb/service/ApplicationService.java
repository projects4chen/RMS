package com.spdb.service;

import com.spdb.pojo.Application;
import com.spdb.vo.ApplicationVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {
    /**
     * 添加一个新的申请
     * @param machineId
     * @param userId
     * @param reason
     */
    void addApplication(Long machineId, Long userId, String reason);

    /**
     * 判断该用户是否已经提交了对该机器的申请
     * @param machineId
     * @param userId
     * @return
     */
    boolean isBeingApplied(Long machineId, Long userId);

    /**
     * 获取用户所有的申请
     * @param userId
     * @return
     */
    List<ApplicationVo> getApplicationsByUserId(Long userId);

    /**
     * 根据id获取申请的内容
     * @param appId
     * @return
     */
    Application getAppById(Long appId);

    /**
     * 修改申请理由
     * @param appId
     * @param appBody
     */
    void updateApp(Long appId, String appBody);
}
