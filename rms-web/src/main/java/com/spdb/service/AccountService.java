package com.spdb.service;

import com.spdb.pojo.Account;
import com.spdb.pojo.SharedAccount;
import com.spdb.vo.AccountVo;
import com.spdb.vo.SharedAccountVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    /**
     * 获取所有的账号信息
     * @return
     */
    List<AccountVo> getAllAccounts();

    /**
     * 添加账号
     * @param account
     */
    void addAccount(Account account);

    /**
     * 根据id获取账号信息
     * @param id
     * @return
     */
    Account getAccountById(Long id);

    /**
     * 更新账号
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 根据id删除账号
     * @param id
     */
    void deleteAccount(Long id);

    /**
     * 申请共享账号
     * @param accountId
     * @param userId
     */
    void shareAccount(Long accountId, Long userId);

    /**
     * 根据用户id获取分享给他且未处理的共享请求
     * @param userId
     * @return
     */
    List<SharedAccountVo> getSharedRecordsByUserId(Long userId);

    /**
     * 根据用户id获取自己拥有和共享的账号
     * @param userId
     * @return
     */
    List<AccountVo> getOwnedAndSharedAccount(Long userId);

    /**
     * 处理账号申请的请求，1为接收，0为拒绝
     * @param accountId
     * @param agree
     */
    void processApp(Long accountId, int agree);
}
