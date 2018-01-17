package com.mtf.admin.common.constant;

public interface Constant {
    /**
     * 超级管理员
     */
    Integer AGENCY_TYPE_1 = 1;

    /**
     * 超级代理
     */
    Integer AGENCY_TYPE_2 = 2;

    /**
     * 普通代理
     */
    Integer AGENCY_TYPE_3 = 3;

    /**
     * 充值类型: 给代理充值
     */
    Integer RECORD_TYPE_AGENCY = 1;

    /**
     * 充值类型: 给玩家充值
     */
    Integer RECORD_TYPE_USER = 2;

    /**
     * 审批状态  提交(审批中/待审批)
     */
    Integer ENCHASHMENT_STATUS_SUBMIT = 1;

    /**
     * 审批状态 通过
     */
    Integer ENCHASHMENT_STATUS_PASS = 2;

    /**
     * 审批状态 拒绝
     */
    Integer ENCHASHMENT_STATUS_REJECT = 3;

}
