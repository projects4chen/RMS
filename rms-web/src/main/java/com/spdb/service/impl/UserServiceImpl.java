package com.spdb.service.impl;

import com.spdb.mapper.UserMapper;
import com.spdb.pojo.User;
import com.spdb.service.UserService;
import com.spdb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserVo> getAllUsers() {
        List<User> users = userMapper.getAllUsers();
        List<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = new UserVo();
            // 根据身份类型返回相应的字符串
            Long identity = user.getIdentity();
            String ident = "";
            if (identity == 0L) {
                ident = "普通用户";
            } else if (identity == 1L) {
                ident = "管理员";
            } else if (identity == 2L) {
                ident = "超级管理员";
            }
            userVo.setIdentity(ident);
            // 其他内容直接复制
            userVo.setUserId(user.getUserId());
            userVo.setUsername(user.getUsername());
            userVo.setName(user.getName());
            userVo.setPassword(user.getPassword());
            userVos.add(userVo);
        }
        return userVos;
    }
}
