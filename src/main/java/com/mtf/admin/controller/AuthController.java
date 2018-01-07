package com.mtf.admin.controller;

import com.mtf.admin.common.annotation.PublicMethod;
import com.mtf.admin.common.config.Role;
import com.mtf.admin.common.util.Token;
import com.mtf.admin.common.vo.AuthVO;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.ResultData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{

    @RequestMapping("get")
    @PublicMethod
    public ResultData getAuths(
            String authKey,
            String authPass
    ) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        //TODO 等那个傻逼给我表再改吧
        if(authKey.equals("admin") && authPass.equals("admin")){
            AuthVO auth = new AuthVO();
            auth.setUcode(authKey);
            auth.setAuthTime(new Date());
            auth.setRole(Role.admin);
            auth.setToken(Token.generate(auth));
            return success(auth);
        }
        return error();
    }
}
