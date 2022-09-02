package com.spdb.controller;

import com.spdb.pojo.Account;
import com.spdb.pojo.User;
import com.spdb.service.AccountService;
import com.spdb.utils.UserThreadLocal;
import com.spdb.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/accountInfo")
    public String accountInfo(Model model){
        List<AccountVo> accountVos = accountService.getAllAccounts();
//        for (AccountVo accountVo : accountVos) {
//            System.out.println(accountVo);
//        }
        model.addAttribute("accounts", accountVos);
        return "/account/list";
    }

    @RequestMapping("/toAddAccountPage")
    public String toAddAccountPage(){
        return "/account/add";
    }

    @RequestMapping("/addAccount")
    public String addAccount(Account account){
        accountService.addAccount(account);
        return "redirect:/account/accountInfo";
    }

    @RequestMapping("/toUpdateAccountPage")
    public String toUpdateAccountPage(Model model, @RequestParam("id") Long id){
        // 获取账号的信息
        Account account = accountService.getAccountById(id);
        // 返回前端
        model.addAttribute("account", account);
        return "/account/update";
    }

    @RequestMapping("/updateAccount")
    public String updateAccount(Account account){
        accountService.updateAccount(account);
        return "redirect:/account/accountInfo";
    }

    @RequestMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("id") Long id){
        accountService.deleteAccount(id);
        return "redirect:/account/accountInfo";
    }
}
