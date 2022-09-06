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

    // 根据id获取账号
    Account getAccountById(Long id);

    // 更新账号信息
    void updateAccount(Account account);

    // 根据id删除账号
    void deleteAccount(Long id);

    /**
     * 根据用户id查出拥有和共享的账号信息
     * @param userId
     * @return
     */
    List<AccountVo> getOwnedAndSharedAccount(Long userId);
}
