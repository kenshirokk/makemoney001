package com.mtf.admin.common.vo;

import com.mtf.admin.entity.AccountsInfo;
import lombok.Data;

import java.util.Base64;

@Data
public class AccountsInfoVO extends AccountsInfo {

    private String agencyNickname;      //代理昵称(推荐人昵称)
    private byte[] customFace;          //用户头像
    private Integer score;              //金币
    private Integer insureScore;        //房卡

    public String getCustomFaceSrc() {
        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(customFace);
    }

}
