package com.mtf.admin.service.impl;

import com.mtf.admin.entity.GameConfig;
import com.mtf.admin.mapper.adminmanager.GameConfigMapper;
import com.mtf.admin.service.GameConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameConfigServiceImpl implements GameConfigService {

    @Autowired
    private GameConfigMapper gameConfigMapper;

    @Override
    public List<GameConfig> findAll() {
        return gameConfigMapper.findAll();
    }

    @Override
    public int update(GameConfig gameConfig) {
        return gameConfigMapper.update(gameConfig);
    }
}
