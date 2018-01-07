package com.mtf.admin.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthVO {
    private String ucode;
    private Integer role;
    private Date authTime;
    private String token;
}
