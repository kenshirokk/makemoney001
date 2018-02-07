package com.mtf.admin.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PerformanceDoubleVO implements Serializable {
    private Integer month;
    private Double levelOneMoney;
    private Double levelTwoMoney;
}
