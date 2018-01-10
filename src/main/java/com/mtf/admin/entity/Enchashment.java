package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Enchashment implements Serializable {
    private Integer id;
    private Integer agencyId;               //代理人id
    private Integer enchashmentType;        //提现类型
    private Integer money;                  //提现金额
    private String weixin;
    private String alipay;
    private String bankno;
    private Integer approveStatus;          //审批状态
    private Date createDatetime;            //申请时间
    private Date approveDatetime;           //审批时间
}
