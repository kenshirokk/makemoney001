package com.mtf.admin.common.vo;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ResultData<T>{
    private int code;
    private String message;
    private Map<String,Object> data;

    public ResultData(){
        super();
        this.code = ResultCode.success;
        this.message = ResultCode.get(code);
        this.data = Maps.newHashMap();
    }

    public ResultData(int code){
        this.code = code;
        this.message = ResultCode.get(code);
    }

    public ResultData(Map<String,Object> data){
        this();
        this.data = data;
    }

    public ResultData(List<T> list){
        this();
        this.data.put("list",list);
    }

    public ResultData(T t){
        this();
        this.data.put("entity",t);
    }

    public ResultData set(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}
