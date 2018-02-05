package com.mtf.admin.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PerformanceDetailVO implements Serializable {

    private Integer userId;
    private String nickname;
    private Integer backMoney;
    private Integer costMoney;
    private Date payDatetime;

}
