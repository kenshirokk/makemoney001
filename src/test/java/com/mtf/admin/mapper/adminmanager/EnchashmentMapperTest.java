package com.mtf.admin.mapper.adminmanager;

import com.google.common.collect.Maps;
import com.mtf.admin.entity.Enchashment;
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
public class EnchashmentMapperTest {

    @Autowired
    private EnchashmentMapper enchashmentMapper;

    @Test
    public void findAll() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyNickname", "test");
        List<Enchashment> all = enchashmentMapper.findAll(params);
        System.out.println(all.size());
        params.put("agencyId", 9);
        all = enchashmentMapper.findAll(params);
        System.out.println(all.size());
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }
}