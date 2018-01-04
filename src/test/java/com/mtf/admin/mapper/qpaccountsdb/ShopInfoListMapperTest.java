package com.mtf.admin.mapper.qpaccountsdb;

import com.github.pagehelper.PageHelper;
import com.mtf.admin.entity.ShopInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
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
}