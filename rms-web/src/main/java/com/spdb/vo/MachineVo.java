package com.spdb.vo;

import lombok.Data;

@Data
public class MachineVo {
    private Long machineId;

    private String ip;

    private String name;

    private String registerDate;

    private String sid;

    private String config;

    private Long userId;

    private String useInfo;

    private String env;
}
