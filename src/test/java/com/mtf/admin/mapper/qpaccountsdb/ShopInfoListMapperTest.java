package com.mtf.admin.mapper.qpaccountsdb;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mtf.admin.entity.ShopInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
@Transactional
public class ShopInfoListMapperTest {

    @Autowired
    private ShopInfoListMapper shopInfoListMapper;

    @Test
    public void findAll() {
        PageHelper.startPage(1,2);
        List<ShopInfo> all = shopInfoListMapper.findAll();
        for (ShopInfo shopInfo : all) {
            System.out.println(shopInfo);
        }
    }

    @Test
    public void update() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("itemId", 1);
        params.put("GoodsNum", 123);
        params.put("Price", 321);
        int i = shopInfoListMapper.update(params);
    }
}