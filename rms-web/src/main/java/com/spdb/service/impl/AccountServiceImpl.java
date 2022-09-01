package com.spdb.service.impl;

import com.spdb.mapper.AccountMapper;
import com.spdb.mapper.UserMapper;
import com.spdb.pojo.Account;
import com.spdb.pojo.User;
import com.spdb.service.AccountService;
import com.spdb.utils.UserThreadLocal;
import com.spdb.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<AccountVo> getAllAccounts() {
        return accountMapper.getAllAccounts();
    }

    @Override
    public void addAccount(Account account) {
        // 获取当前用户的id，给ownerID（账号拥有者id）赋值
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.getUserByUsername(username);
        // 设置账号所属id
        account.setOwnerId(user.getUserId());
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

    @Override
    public void deleteAccount(Long id) {
        accountMapper.deleteAccount(id);
    }
}
