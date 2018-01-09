package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameConfig implements Serializable {

    private Integer id;
    private String paramName;
    private String paramValue;
    private String paramDesc;

}
