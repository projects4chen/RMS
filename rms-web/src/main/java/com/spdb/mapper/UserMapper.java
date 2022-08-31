package com.spdb.mapper;

import com.spdb.pojo.User;
import com.spdb.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    // 根据id查用户名
    String getUserNameById(Long userId);

    // 查出所有的用户
    List<UserVo> getAllUsers();

    // 添加一个用户
    void addUser(User user);

    // 根据id删除用户
    void deleteUser(Long id);

    // 更新用户信息
    void updateUser(User user);

    // 根据id查用户信息
    User getUserById(Long id);

    // 根据username获取用户
    User getUserByUsername(String username);
}
