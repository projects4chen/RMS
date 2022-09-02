package com.spdb.mapper;

import com.spdb.pojo.Application;
import com.spdb.vo.ApplicationVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationMapper {

    /**
     * 添加一个新的申请
     * @param application
     */
    void addApplication(Application application);

    /**
     * 获取
     * @param machineId
     * @param userId
     * @return
     */
    String getAppState(Long machineId, Long userId);

    /**
     * 根据用户id获取其所有的申请，按时间排序
     * @param userId
     * @return
     */
    List<ApplicationVo> getAppsByUserId(Long userId);

    /**
     * 根据id获取申请内容
     * @param appId
     * @return
     */
    Application getAppById(Long appId);

    /**
     * 更新申请
     * @param application
     */
    void updateApp(Application application);

    /**
     * 获取所有待审批的申请
     * @return
     */
    List<ApplicationVo> getAllPendingApps();
}
