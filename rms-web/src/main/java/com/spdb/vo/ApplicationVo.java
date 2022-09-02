package com.spdb.vo;

import lombok.Data;

@Data
public class ApplicationVo {
    private Long appId;

    private String appDate;

    private Long applicantId;

    private String userName;

    private Long machineId;

    private String machineName;

    private String machineIp;

    private String machineConfig;

    private String state;

    // 申请理由
    private String appBody;

    // 审批意见
    private String repBody;
}
