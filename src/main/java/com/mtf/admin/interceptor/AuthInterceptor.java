package com.mtf.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mtf.admin.common.util.Token;
import com.mtf.admin.common.vo.AuthVO;
import com.mtf.admin.common.vo.ResultCode;
import com.mtf.admin.common.vo.ResultData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String authStr = httpServletRequest.getParameter("auth");
        AuthVO auth = JSONObject.toJavaObject(JSON.parseObject(authStr),AuthVO.class);

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
        return true;
    }

    private void returnJson(String json,HttpServletResponse response) throws IOException {
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
