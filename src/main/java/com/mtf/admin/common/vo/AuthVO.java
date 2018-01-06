package com.mtf.admin.common.vo;

import com.mtf.admin.common.config.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthVO {
    private String ucode;
    private Role role;
    private Date authTime;
    private String token;
}
