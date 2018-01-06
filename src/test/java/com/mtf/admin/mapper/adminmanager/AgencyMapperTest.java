package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.Agency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
public class AgencyMapperTest {

    @Autowired
    private AgencyMapper agencyMapper;

    @Test
    public void findOne() {
        Agency one = agencyMapper.findOne(1);
    }
}