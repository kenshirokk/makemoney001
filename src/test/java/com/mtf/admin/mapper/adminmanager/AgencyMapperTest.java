package com.mtf.admin.mapper.adminmanager;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mtf.admin.entity.Agency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
public class AgencyMapperTest {

    @Autowired
    private AgencyMapper agencyMapper;

    @Test
    public void findOne() {
        Agency one = agencyMapper.findOne(1);
        System.out.println(one);
    }

    @Test
    public void save() throws IOException {
        Agency a = new Agency();
        a.setAgencyType(2);
        File f = new File("d:/1.jpg");
        a.setAvatar(Files.readAllBytes(f.toPath()));
        agencyMapper.save(a);
    }

    @Test
    public void findAll() {
        Map<String, Object> params = Maps.newHashMap();
//        params.put("agencyId", 16);
//        params.put("parentId", 9);
//        params.put("nickname", "2");
        PageHelper.startPage(1, 22).setOrderBy("id DESC");
        List<Agency> all = agencyMapper.findAll(9, null, params);
        for (Agency agency : all) {
            System.out.println(agency.getAvatar());
            System.out.println(agency.getAvatarSrc());
        }
    }
}