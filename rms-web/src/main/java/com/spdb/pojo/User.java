package com.spdb.pojo;

import lombok.Data;

@Data
public class User {

    private Long userId;

    private String username;

    private String nickname;

    private String password;

    private Long identity;

}
