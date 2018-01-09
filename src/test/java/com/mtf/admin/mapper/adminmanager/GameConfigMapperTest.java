package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.GameConfig;
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
public class GameConfigMapperTest {

    @Autowired
    private GameConfigMapper gameConfigMapper;

    @Test
    public void findAll() {
        List<GameConfig> list = gameConfigMapper.findAll();
    }

    @Test
    public void update() {
        GameConfig gc = new GameConfig();
        gc.setId(2);
        gc.setParamValue("666");
        int i = gameConfigMapper.update(gc);
    }
}