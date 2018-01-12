package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 金币充值记录表
 * 1: 给代理 2: 给玩家
 */
@Data
public class CoinRecord implements Serializable {

    private Integer id;                 //PK
    private Integer record_type;        //类型 1:给代理 2:给玩家
    private Integer fromAgencyId;       //操作者代理ID  金钱输出者
    private Integer toAgencyId;         //转给代理的id
    private Integer toUserId;           //转给玩家的id
    private Integer quantity;           //数量
    private Date transDatetime;         //充值时间

}
