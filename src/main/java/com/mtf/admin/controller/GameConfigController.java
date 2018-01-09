package com.mtf.admin.controller;

import com.github.pagehelper.PageInfo;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.GameConfig;
import com.mtf.admin.service.GameConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gameConfig")
public class GameConfigController extends BaseController {

    @Autowired
    private GameConfigService gameConfigService;

    /**
     * 列表展示所有
     * @return
     */
    @RequestMapping
    public ResultData list() {
        List<GameConfig> list = gameConfigService.findAll();
        PageInfo<GameConfig> page = new PageInfo<>(list);
        return success(list).set("total", page.getTotal());
    }

    /**
     * 根据id 更新 param_value
     * @param gameConfig
     * @return
     */
    @PostMapping("update")
    public ResultData update(GameConfig gameConfig) {
        int i = gameConfigService.update(gameConfig);
        return i > 0 ? success() : error();
    }
}
