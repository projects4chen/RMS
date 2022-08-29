package com.spdb.mapper;

import com.spdb.pojo.Account;
import com.spdb.vo.AccountVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    // 获取所有的账号信息
    List<AccountVo> getAllAccounts();

    // 添加账号
    void addAccount(Account account);
}
