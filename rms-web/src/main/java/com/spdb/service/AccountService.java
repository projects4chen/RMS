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
}
