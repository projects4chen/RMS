package com.spdb.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    // 根据id查用户名
    String getUserNameById(Long userId);
}
