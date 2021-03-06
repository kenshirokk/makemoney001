package com.mtf.admin.mapper.qpaccountsdb;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mtf.admin.common.vo.AccountsInfoVO;
import com.mtf.admin.entity.AccountsInfo;
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
public class AccountsInfoMapperTest {

    @Autowired
    private AccountsInfoMapper accountsInfoMapper;

    @Test
    public void findAll() {
        Map<String, Object> params = Maps.newHashMap();
//        params.put("userId", 111);
//        params.put("", );
//        params.put("", );
        PageHelper.startPage(1, 1111);
        List<AccountsInfoVO> list = accountsInfoMapper.findAll(17, 4, params);
        for (AccountsInfoVO ai : list) {
            System.out.println(ai.getUserID() + ai.getNickName() +ai);
        }
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
        AccountsInfo ai = accountsInfoMapper.findOne(1);
        System.out.println(ai);
    }

    @Test
    public void updateCoinPlus() {
        int i = accountsInfoMapper.updateCoinPlus(100, 666);
        System.out.println(i);
    }

    @Test
    public void updateRoomCardPlus() {
        int i = accountsInfoMapper.updateRoomCardPlus(100, 666);
        System.out.println(i);
    }

    @Test
    public void getCount() {
        Integer count = accountsInfoMapper.getCount(17, 0);
        System.out.println(count);
    }
}