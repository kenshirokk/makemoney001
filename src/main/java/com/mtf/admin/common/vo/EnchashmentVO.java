package com.mtf.admin.common.vo;

import com.mtf.admin.entity.Enchashment;
import lombok.Data;

/**
 * 代理昵称
 * 代理级别
 * 代理头像
 */
@Data
public class EnchashmentVO extends Enchashment {
    private String agencyNickname;
    private Integer agencyType;
    private byte[] avatar;
}
