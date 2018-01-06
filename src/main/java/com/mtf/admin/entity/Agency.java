package com.mtf.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 代理表(代理人表/管理员表/管理用户表)
 * 1: 超级管理员; 2: 超级代理; 3: N级代理
 * 超级管理员只有一个,初始化系统时插入数据库
 * 超级代理 & N级代理  均为输入用户ID  从用户表中复制过来
 */
@Data
public class Agency implements Serializable {
    private Integer id;
}
