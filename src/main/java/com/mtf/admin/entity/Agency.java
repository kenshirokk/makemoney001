package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理表(代理人表/管理员表/管理用户表)
 * 1: 超级管理员; 2: 超级代理; 3: N级代理
 * 超级管理员只有一个,初始化系统时插入数据库
 * 超级代理 & N级代理  均为输入用户ID  从用户表中复制过来
 */
@Data
public class Agency implements Serializable {

    private Integer id;             //id 主键
    private Integer agencyType;     //代理类型
    private Integer parentId;       //上级代理id
    private String parentNickname;  //上级代理昵称
    private String password;        //密码
    private String nickname;        //昵称
    private String avatar;          //头像
    private String phone;           //电话
    private Integer roomCard;       //房卡
    private Integer coin;           //金币
    private Integer agencyBalance;  //代理余额
    private Integer disable;        //禁用
    private Integer deleted;        //删除
    private Date createDate;        //创建时间
    private Integer creatorId;      //创建者id
    private Integer userId;         //用户id
    private String qrcode;          //推荐二维码
}
