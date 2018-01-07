package com.mtf.admin.service.impl;

import com.mtf.admin.entity.ShopInfo;
import com.mtf.admin.mapper.qpaccountsdb.ShopInfoListMapper;
import com.mtf.admin.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopInfoServiceImpl implements ShopInfoService {

    @Autowired
    private ShopInfoListMapper shopInfoListMapper;

    @Override
    public List<ShopInfo> findAll() {
        return shopInfoListMapper.findAll();
    }

    @Override
    public int update(Map<String, Object> params) {
        return shopInfoListMapper.update(params);
    }

}
