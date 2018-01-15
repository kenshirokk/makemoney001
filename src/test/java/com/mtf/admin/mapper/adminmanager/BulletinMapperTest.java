package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.Bulletin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("k")
@Transactional
public class BulletinMapperTest {

    @Autowired
    private BulletinMapper bulletinMapper;

    @Test
    public void findAll() {
        List<Bulletin> list = bulletinMapper.findAll();
    }

    @Test
    public void save() throws IOException {
        Bulletin b = new Bulletin();
        b.setTitle("test");
        b.setContent("cont");
        b.setDesc("ddd");
        b.setImage("image.....");
        int i = bulletinMapper.save(b);
    }

    @Test
    public void update() throws IOException {
        Bulletin b = new Bulletin();
        b.setId(1);
        b.setTitle("newtitle");
        b.setContent("newcont");
        b.setDesc("newddd");
        b.setImage("image.....");
        int i = bulletinMapper.update(b);
    }

    @Test
    public void delete() {
        int i = bulletinMapper.delete(1);
    }
}