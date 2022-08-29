package com.spdb.vo;

import lombok.Data;

@Data
public class AccountVo {

    private Long accId;

    private String desp;

    private String username;

    private String password;

    private String owner;
}
