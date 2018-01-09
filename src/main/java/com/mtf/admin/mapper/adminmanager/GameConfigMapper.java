package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.GameConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameConfigMapper {

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
