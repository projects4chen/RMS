package com.spdb.pojo;

import lombok.Data;

@Data
public class SharedAccount {

    private Long sharedId;

    private Long accountId;

    private Long toWhoId;

    private Long sharedDate;

    private String state;
}
