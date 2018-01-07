package com.mtf.admin.common.vo;

import com.mtf.admin.entity.Agency;
import com.mtf.admin.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public abstract class BaseController<T>{

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private HttpServletRequest request;

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


    protected Agency getLoginUser(){
        AuthVO auth = (AuthVO)request.getAttribute("auth");
        return agencyService.findOneByLoginKey(auth.getUcode());
    }
}
