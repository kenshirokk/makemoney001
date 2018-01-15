package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Bulletin implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private String desc;
    private String image;
    private Date createDatetime;
    private Date updateDatetime;
}
