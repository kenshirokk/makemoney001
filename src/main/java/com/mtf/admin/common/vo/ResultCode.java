package com.mtf.admin.common.vo;

import com.google.common.collect.Maps;

import java.util.Map;

public class ResultCode {

    public final static int success = 200;
    public final static int error = 500;//未知异常
    public final static int paramsError = 400;//参数异常

    protected ResultCode(){}

    private static Map<Integer,String> resultMsg = Maps.newHashMap();

    static{
        resultMsg.put(error,"未知异常");
        resultMsg.put(paramsError,"参数异常");
    }

    public static String get(int key){
        return resultMsg.get(key);
    }
}
