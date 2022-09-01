package com.spdb.service;

import com.spdb.pojo.User;
import com.spdb.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * 获取所有用户信息
     * @return
     */
    List<UserVo> getAllUsers();

    /**
     * 添加一个用户
     * @param user
     */
    boolean addUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id查用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据用户名获取user
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
