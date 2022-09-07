package com.spdb.service.impl;

import com.spdb.mapper.AccountMapper;
import com.spdb.mapper.SharedAccountMapper;
import com.spdb.mapper.UserMapper;
import com.spdb.pojo.Account;
import com.spdb.pojo.SharedAccount;
import com.spdb.pojo.User;
import com.spdb.service.AccountService;
import com.spdb.service.UserInfoService;
import com.spdb.utils.UserThreadLocal;
import com.spdb.vo.AccountVo;
import com.spdb.vo.SharedAccountVo;
import com.sun.xml.internal.ws.message.saaj.SAAJHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SharedAccountMapper sharedAccountMapper;

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

    @Override
    public void shareAccount(Long accountId, Long userId) {
        // 1. 校验用户是否为账号属主且未共享过
        User user = userInfoService.getUser();
        Account account = accountMapper.getAccountById(accountId);
        if (account.getOwnerId().equals(user.getUserId()) && sharedAccountMapper.getRecordByAccAndUserId(accountId, userId) == null){
            // 2. 往数据库中添加共享记录
            SharedAccount sharedAccount = new SharedAccount();
            // 初始化
            sharedAccount.setAccountId(accountId);
            sharedAccount.setSharedDate(new Date().getTime());
            sharedAccount.setToWhoId(userId);
            sharedAccount.setState("待处理");
            // 添加
            sharedAccountMapper.addRecord(sharedAccount);
        }



    }

    @Override
    public List<SharedAccountVo> getSharedRecordsByUserId(Long userId) {
        return sharedAccountMapper.getSharedRecordsByUserId(userId);
    }

    @Override
    public List<AccountVo> getOwnedAndSharedAccount(Long userId) {
        return accountMapper.getOwnedAndSharedAccount(userId);
    }

    @Override
    public void processApp(Long accountId, int agree) {
        String result = "";
        if (agree == 1){
            result = "已同意";
        } else {
            result = "已拒绝";
        }
        sharedAccountMapper.processApp(accountId, result);
    }
}
