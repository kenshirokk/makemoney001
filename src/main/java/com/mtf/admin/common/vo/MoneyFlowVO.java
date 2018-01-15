package com.mtf.admin.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MoneyFlowVO implements Serializable {

    private Integer month;
    private Long money;

}
