package com.mtf.admin.common.vo;

import java.util.List;
import java.util.Map;

public abstract class BaseController<T>{

    protected ResultData<T> success(){
        return new ResultData<T>();
    }

    protected ResultData<T> error(){
        return new ResultData<T>(ResultCode.error);
    }

    protected ResultData<T> success(List<T> list){
        return new ResultData<T>(list);
    }

    protected ResultData<T> success(T entity){
        return new ResultData<T>(entity);
    }

    protected ResultData<T> success(Map<String,Object> data){
        return new ResultData<T>(data);
    }

    protected ResultData<T> ret(int code){
        return new ResultData<T>(code);
    }
}
