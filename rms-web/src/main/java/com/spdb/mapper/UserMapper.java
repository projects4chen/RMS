package com.spdb.mapper;

import com.spdb.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    // 根据id查用户名
    String getUserNameById(Long userId);

    // 查出所有的用户
    List<User> getAllUsers();
}
