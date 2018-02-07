package com.mtf.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mtf.admin.common.annotation.PublicMethod;
import com.mtf.admin.common.util.Cryptography;
import com.mtf.admin.common.util.QrCode;
import com.mtf.admin.common.util.Token;
import com.mtf.admin.common.vo.AuthVO;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.service.AgencyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{

    @Value("${config.uploadPath}")
    private String uploadPath;

    @Value("${config.inviteUrl}")
    private String inviteUrl;

    @Autowired
    private AgencyService agencyService;


    @RequestMapping("get")
    @PublicMethod
    public ResultData getAuths(
            @RequestParam("authKey") String authKey,
            @RequestParam("authPass")String authPass,
            HttpServletResponse response
    ) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        if(StringUtils.isNumeric(authKey)){
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
                if(StringUtils.isBlank(loginUser.getQrcode())){
                   try {
                       //生成 二维码
                       Path path = QrCode.genQrCode(inviteUrl + loginUser.getId(), 500, 500, "jpg", loginUser.getId() + ".jpg",
                               uploadPath);
                       //添加 头像logo
                       path = QrCode.addLogo(path, loginUser.getAvatar(), "jpg", uploadPath);

                       //读取背景图片
                       ClassPathResource classPathResource = new ClassPathResource("background.jpg");
                       InputStream inputStream = classPathResource.getInputStream();
                       File file = new File("background.jpg");
                       Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                       Path result = file.toPath();

                       //添加二维码
                       result = QrCode.addLogo(result, path, "jpg", path.getFileName().toString(), uploadPath);

                       result = QrCode.addWord(result, "昵称: " + loginUser.getNickname(), "jpg", 170, 300, 30);
                       result = QrCode.addWord(result, "邀请码: " + loginUser.getId(), "jpg", 170, 350, 30);

                       Map<String, Object> params2 = Maps.newHashMap();
                       params2.put("qrcode", result.getFileName().toString());
                       params2.put("agencyId", loginUser.getId());
                       agencyService.update(params2);
                   }catch (Exception e){
                    e.printStackTrace();
                   }
                }
                return success(auth);
            }
        }
        return error("密码不正确");
    }


    @RequestMapping("getUserInfo")
    public ResultData getUserInfo() {
        Agency user  = super.getLoginUser();
        user.setAgencyBalance(user.getAgencyBalance() == null ? 0 : user.getAgencyBalance() / 100);
        return success(user);
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
