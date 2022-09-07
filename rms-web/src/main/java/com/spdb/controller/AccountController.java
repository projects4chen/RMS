package com.spdb.controller;

import com.spdb.pojo.Account;
import com.spdb.pojo.SharedAccount;
import com.spdb.pojo.User;
import com.spdb.service.AccountService;
import com.spdb.service.UserInfoService;
import com.spdb.service.UserService;
import com.spdb.vo.AccountVo;
import com.spdb.vo.SharedAccountVo;
import com.spdb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserService userService;

    @RequestMapping("/accountInfo")
    public String accountInfo(Model model){
        userInfoService.retUserInfo(model);
        // 获取当前用户id
        User user = userInfoService.getUser();
        List<AccountVo> accountVos = accountService.getOwnedAndSharedAccount(user.getUserId());
//        for (AccountVo accountVo : accountVos) {
//            System.out.println(accountVo);
//        }
        model.addAttribute("accounts", accountVos);
        return "/account/list";
    }

    @RequestMapping("/toAddAccountPage")
    public String toAddAccountPage(Model model){
        userInfoService.retUserInfo(model);
        return "/account/add";
    }

    @RequestMapping("/addAccount")
    public String addAccount(Account account){
        accountService.addAccount(account);
        return "redirect:/account/accountInfo";
    }

    @RequestMapping("/toUpdateAccountPage")
    public String toUpdateAccountPage(Model model, @RequestParam("id") Long id){
        userInfoService.retUserInfo(model);
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

    @RequestMapping("/toShareAccountPage")
    public String toShareAccountPage(@RequestParam("accountId") Long accountId, Model model){
        userInfoService.retUserInfo(model);
        // 所有用户信息
        List<UserVo> users = userService.getAllUsers();
        model.addAttribute("users", users);
        // 账号信息
        Account account = accountService.getAccountById(accountId);
        model.addAttribute("account", account);
        return "/account/share";
    }

    @RequestMapping("/shareAccount")
    public String shareAccount(@RequestParam("accountId") Long accountId, @RequestParam("userId") Long userId){
        accountService.shareAccount(accountId, userId);
        return "redirect:/account/accountInfo";
    }

    @RequestMapping("/toAppPage")
    public String toAppPage(@RequestParam("userId") Long userId, Model model){
        userInfoService.retUserInfo(model);
        List<SharedAccountVo> sharedAccounts = accountService.getSharedRecordsByUserId(userId);
//        for (SharedAccountVo sharedAccount : sharedAccounts) {
//            System.out.println(sharedAccount);
//        }
        model.addAttribute("accounts", sharedAccounts);
        return "/account/application";
    }

    @RequestMapping("/acceptSharedAccount")
    public String acceptSharedAccount(@RequestParam("accountId") Long accountId, @RequestParam("agree") int agree){
        accountService.processApp(accountId, agree);
        return "redirect:/account/accountInfo";
    }

}
