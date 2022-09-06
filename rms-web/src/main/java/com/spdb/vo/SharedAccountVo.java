package com.spdb.vo;

import lombok.Data;

@Data
public class SharedAccountVo {
    private Long sharedId;

    private Long accountId;

    private String username;

    private Long toWhoId;

    private String owner;

    private String desp;

    private Long sharedDate;

    private String state;
}
