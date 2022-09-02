package com.spdb.pojo;

import lombok.Data;

@Data
public class Application {

    private Long appId;

    private Long appDate;

    private Long applicantId;

    private Long machineId;

    private String state;

    // 申请理由
    private String appBody;

    // 审批意见
    private String repBody;
}
