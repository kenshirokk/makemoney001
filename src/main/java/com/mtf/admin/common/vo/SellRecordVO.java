package com.mtf.admin.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SellRecordVO implements Serializable {

    private Integer orderId;
    private Integer userId;
    private String userNickname;
    private String userImage;
    private Integer agencyId;
    private String agencyNickname;
    private String agencyImage;
    private Integer getType;
    private Integer getNums;
    private Integer costMoney;
    private Date orderDatetime;

}
