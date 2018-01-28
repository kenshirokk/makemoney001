package com.mtf.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mtf.admin.common.annotation.AdminMethod;
import com.mtf.admin.common.annotation.PublicMethod;
import com.mtf.admin.common.config.Role;
import com.mtf.admin.common.util.Token;
import com.mtf.admin.common.vo.AuthVO;
import com.mtf.admin.common.vo.ResultCode;
import com.mtf.admin.common.vo.ResultData;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HandlerMethod method = (HandlerMethod)o;
        PublicMethod pub = method.getMethod().getAnnotation(PublicMethod.class);
        if(pub != null){
            return true;
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        List<Cookie> list = Arrays.stream(cookies).filter(c -> c.getName().equals("auth")).collect(Collectors.toList());

        AuthVO auth = list != null && list.size()>0 ?JSONObject.toJavaObject(JSON.parseObject(URLDecoder.decode(list.get(0).getValue(),"UTF-8")),AuthVO.class): null;
        if(auth == null){
            returnJson(JSONObject.toJSONString(new ResultData(ResultCode.paramsError)),httpServletResponse);
            return false;
        }
        Date authTime = auth.getAuthTime();
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_YEAR,-1);
        if(authTime.before(now.getTime())){
            returnJson(JSONObject.toJSONString(new ResultData(ResultCode.authExpired)),httpServletResponse);
            return false;
        }

        String serverToken = Token.generate(auth);
        if(!auth.getToken().equals(serverToken)){
            returnJson(JSONObject.toJSONString(new ResultData(ResultCode.authError)),httpServletResponse);
            return false;
        }
        if(!auth.getRole().equals(Role.admin) && method.getMethod().getAnnotation(AdminMethod.class) != null){
            returnJson(JSONObject.toJSONString(new ResultData(ResultCode.permissionDenied)),httpServletResponse);
            return false;
        }
        httpServletRequest.setAttribute("auth",auth);
        return true;
    }

    private void returnJson(String json,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(json.toString());
        writer.flush();
        writer.close();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
