package com.spdb.controller;

import com.spdb.pojo.Account;
import com.spdb.service.AccountService;
import com.spdb.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/accountInfo")
    public String accountInfo(Model model){
        List<AccountVo> accountVos = accountService.getAllAccounts();
        for (AccountVo accountVo : accountVos) {
            System.out.println(accountVo);
        }
        model.addAttribute("accounts", accountVos);
        return "/account/list";
    }

    @RequestMapping("/toAddAccountPage")
    public String toAddAccountPage(){
        return "/account/add";
    }

    @RequestMapping("/addAccount")
    public String addAccount(Model model, Account account){
        // 获取当前用户的id，给ownerID（账号拥有者id）赋值，暂时设为1
        account.setOwnerId(1L);
        accountService.addAccount(account);
        return "redirect:/account/accountInfo";
    }
}
