package com.mtf.admin.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PerformanceVO implements Serializable {
    private Integer month;
    private Integer levelOneMoney;
    private Integer levelTwoMoney;
}
