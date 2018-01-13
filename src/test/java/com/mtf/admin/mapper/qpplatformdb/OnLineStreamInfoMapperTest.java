package com.mtf.admin.mapper.qpplatformdb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
public class OnLineStreamInfoMapperTest {

    @Autowired
    private OnLineStreamInfoMapper onLineStreamInfoMapper;

    @Test
    public void findLimitOne() {
        Integer limitOne = onLineStreamInfoMapper.getOnlineUser();
    }
}