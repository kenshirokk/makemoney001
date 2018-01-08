package com.mtf.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.mtf.admin.common.annotation.PublicMethod;
import com.mtf.admin.common.util.Cryptography;
import com.mtf.admin.common.util.Token;
import com.mtf.admin.common.vo.AuthVO;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{

    @Autowired
    private AgencyService agencyService;

    @RequestMapping("get")
    @PublicMethod
    public ResultData getAuths(
            @RequestParam("authKey") String authKey,
            @RequestParam("authPass")String authPass,
            HttpServletResponse response
    ) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Agency loginUser = agencyService.findByLogin(authKey, Cryptography.md5(authPass));
        if(loginUser != null){
            AuthVO auth = new AuthVO();
            auth.setUcode(authKey);
            auth.setAuthTime(new Date());
            auth.setRole(loginUser.getAgencyType());
            auth.setToken(Token.generate(auth));
            Cookie cookie = new Cookie("auth", URLEncoder.encode(JSONObject.toJSONString(auth),"UTF-8"));
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            return success(auth);
        }
        return error("用户名或密码不正确");
    }


    @RequestMapping("getUserInfo")
    public ResultData getUserInfo() {
        return success(super.getLoginUser());
    }

    @RequestMapping("logout")
    public ResultData logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("auth",null);
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        return success();
    }
}
