package com.mtf.admin.common.vo;

import com.google.common.collect.Maps;

import java.util.Map;

public class ResultCode {

    public final static int success = 200;
    public final static int error = 500;//未知异常
    public final static int paramsError = 400;//参数异常
    public final static int authError = 301;//权限验证失败
    public final static int authExpired= 302;//权限过期
    public final static int permissionDenied = 303;//权限不足

    protected ResultCode(){}

    private static Map<Integer,String> resultMsg = Maps.newHashMap();

    static{
        resultMsg.put(error,"未知异常");
        resultMsg.put(paramsError,"参数异常");
        resultMsg.put(authError,"权限验证失败");
        resultMsg.put(authExpired,"权限过期");
        resultMsg.put(permissionDenied,"权限不足");
    }

    public static String get(int key){
        return resultMsg.get(key);
    }
}
