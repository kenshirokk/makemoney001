package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Performance implements Serializable {

    private Integer id;
    private Integer agencyId;
    private Integer level;
    private Integer money;
    private Date cashBackDatetime;
    private Integer recordBuyOrderId;

}
