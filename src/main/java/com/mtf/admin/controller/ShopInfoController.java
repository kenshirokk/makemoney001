package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.ShopInfo;
import com.mtf.admin.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopInfo")
public class ShopInfoController extends BaseController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private ShopInfoService shopInfoService;

    /**
     * 列表展示
     * @return
     */
    @GetMapping
    public ResultData list(@RequestParam(defaultValue = "1") Integer pageNumber) {
        PageHelper.startPage(pageNumber, PAGE_SIZE);
        List<ShopInfo> list = shopInfoService.findAll();
        return success(list);
    }

    /**
     * 修改 商品数量 & 商品价格
     * @param itemId
     * @param GoodsNum
     * @param Price
     * @return
     */
    @PostMapping
    public ResultData update(Integer itemId, Integer GoodsNum, Integer Price) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("itemId", itemId);
        params.put("GoodsNum", GoodsNum);
        params.put("Price", Price);
        int i = shopInfoService.update(params);
        return i > 0 ? success() : error();
    }
}
