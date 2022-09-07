package com.spdb.mapper;

import com.spdb.pojo.SharedAccount;
import com.spdb.vo.AccountVo;
import com.spdb.vo.SharedAccountVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedAccountMapper {

    /**
     * 添加新的共享账号记录
     * @param sharedAccount
     */
    void addRecord(SharedAccount sharedAccount);

    /**
     * 根据账号id和用户id获取共享记录
     * @param accountId
     * @param userId
     * @return
     */
    SharedAccount getRecordByAccAndUserId(Long accountId, Long userId);

    /**
     * 根据用户id查出所有分享给他的共享请求
     * @param userId
     * @return
     */
    List<SharedAccountVo> getSharedRecordsByUserId(Long userId);


    /**
     * 处理共享请求
     * @param accountId
     * @param result
     */
    void processApp(Long accountId, String result);
}
