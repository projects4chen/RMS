package com.spdb.service;

import com.spdb.pojo.Account;
import com.spdb.vo.AccountVo;
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
}
