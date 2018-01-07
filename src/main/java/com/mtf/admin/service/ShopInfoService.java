package com.mtf.admin.service;

import com.mtf.admin.entity.ShopInfo;

import java.util.List;
import java.util.Map;

public interface ShopInfoService {

    List<ShopInfo> findAll();
    int update(Map<String, Object> params);
}
