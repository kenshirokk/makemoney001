package com.mtf.admin.mapper.qpaccountsdb;

import com.google.common.collect.Maps;
import com.mtf.admin.entity.AccountsInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
public class AccountsInfoMapperTest {

    @Autowired
    private AccountsInfoMapper accountsInfoMapper;

    @Test
    public void findAll() {
        List<AccountsInfo> all = accountsInfoMapper.findAll();
    }

    @Test
    public void updatePlus() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", 1);
        int i = accountsInfoMapper.updatePlus(params);
    }

    @Test
    public void update() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", 1);
        params.put("spreaderID", 1);
        int i = accountsInfoMapper.update(params);
    }

    @Test
    public void findOne() {
        AccountsInfo one = accountsInfoMapper.findOne(1);
    }
}