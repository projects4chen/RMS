package com.spdb.vo;

import lombok.Data;

@Data
public class UserVo {

    private Long userId;

    private String username;

    private String nickname;

    private String password;

    private String identity;
}
