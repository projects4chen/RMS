package com.spdb.pojo;

import lombok.Data;

@Data
public class Account {

    private Long accId;

    private String desp;

    private String username;

    private String password;

    private Long ownerId;
}
