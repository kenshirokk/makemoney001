package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.PageParam;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.ShopInfo;
import com.mtf.admin.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopInfo")
public class ShopInfoController extends BaseController {

    @Autowired
    private ShopInfoService shopInfoService;

    /**
     * 列表展示
     * @return
     */
    @GetMapping
    public ResultData list(PageParam page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ShopInfo> list = shopInfoService.findAll();
        PageInfo<ShopInfo> pageInfo = new PageInfo<ShopInfo>(list);
        return success(list).set("total",pageInfo.getTotal());
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
