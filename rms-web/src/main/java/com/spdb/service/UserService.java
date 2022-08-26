package com.spdb.service;

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
}
