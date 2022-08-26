package com.spdb.pojo;

import lombok.Data;

@Data
public class Machine {

    private Long machineId;

    private String ip;

    private String name;

    private Long registerDate;

    private String sid;

    private String config;

    private Long userId;

    private String env;
}
