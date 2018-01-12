package com.mtf.admin.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mtf.admin.entity.AccountsInfo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountsInfoVO extends AccountsInfo {

    private String agencyNickname;      //代理昵称(推荐人昵称)
    private String customFace;          //用户头像
    private Integer score;              //金币
    private Integer insureScore;        //房卡

}
