package com.mtf.admin.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonalInfoVO implements Serializable {

    private String phone;
    private Long coinSum;
    private Long roomCardSum;
    private Integer userCount;
    private Integer agencyCount;
    private Integer onlineUserCount;
    private Long totalIncome;

}
