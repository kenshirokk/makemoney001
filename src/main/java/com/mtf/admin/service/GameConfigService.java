package com.mtf.admin.service;

import com.mtf.admin.entity.GameConfig;

import java.util.List;

public interface GameConfigService {

    /**
     * 查询所有
     * @return
     */
    List<GameConfig> findAll();

    /**
     * 根据id 更改 param_value
     * @param gameConfig
     * @return
     */
    int update(GameConfig gameConfig);

}
