package com.spdb.service.impl;

import com.spdb.mapper.AccountMapper;
import com.spdb.pojo.Account;
import com.spdb.service.AccountService;
import com.spdb.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<AccountVo> getAllAccounts() {
        return accountMapper.getAllAccounts();
    }

    @Override
    public void addAccount(Account account) {
        accountMapper.addAccount(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountMapper.getAccountById(id);
    }

    @Override
    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
    }
}
