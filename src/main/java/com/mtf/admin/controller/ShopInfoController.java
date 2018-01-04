package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.mtf.admin.entity.ShopInfo;
import com.mtf.admin.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shopInfo")
public class ShopInfoController {

    @Autowired
    private ShopInfoService shopInfoService;

    @RequestMapping
    public List<ShopInfo> list() {
        PageHelper.startPage(1, 2);
        PageHelper.orderBy("OrderID_IOS desc");
        return shopInfoService.findAll();
    }
}
