package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopInfo implements Serializable {
    private Integer ItemID;
    private Integer ItemType;
    private Integer OrderID_IOS;
    private Integer OrderID_Android;
    private Integer Price;
    private Integer GoodsNum;
    private String ItemTitle;
    private String ItemDesc;
    private String ItemIcon;
    private String ItemName;
}
