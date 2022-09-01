package com.spdb.service.impl;

import com.spdb.mapper.UserMapper;
import com.spdb.pojo.User;
import com.spdb.service.UserService;
import com.spdb.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public boolean addUser(User user) {
        // 1. 检查用户是否存在
        User user1 = userMapper.getUserByUsername(user.getUsername());
        if (user1 != null){
            return false;
        }
        // 2. 将用户密码hash
        String pwdHashCode = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(pwdHashCode);
        userMapper.addUser(user);
        return true;
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
