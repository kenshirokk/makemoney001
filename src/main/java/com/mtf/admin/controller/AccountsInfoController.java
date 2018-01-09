package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.PageParam;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.AccountsInfo;
import com.mtf.admin.service.AccountsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accountsInfo")
public class AccountsInfoController extends BaseController {

    @Autowired
    private AccountsInfoService accountsInfoService;

    /**
     * 查询所有  列表展示
     * @param page
     * @return
     */
    @GetMapping
    public ResultData list(PageParam page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<AccountsInfo> list = accountsInfoService.findAll();
        PageInfo<AccountsInfo> pageInfo = new PageInfo<>(list);
        return success(list).set("total", pageInfo.getTotal());
    }

    /**
     * 更新房卡
     * @param userId
     * @param roomCard
     * @return
     */
    @PostMapping("updateRoomCard")
    public ResultData updateRoomCard(Integer userId, Integer roomCard) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("roomCard", roomCard);
        int i = accountsInfoService.updatePlus(params);
        return i > 0 ? success() : error();
    }

    /**
     * 更新金币
     * @param userId
     * @param coin
     * @return
     */
    @PostMapping("updateCoin")
    public ResultData updateCoin(Integer userId, Integer coin) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("coin", coin);
        int i = accountsInfoService.updatePlus(params);
        return i > 0 ? success() : error();
    }

    /**
     * 更新代理人
     * @param userId
     * @param spreaderID
     * @return
     */
    @PostMapping("updateSpreader")
    public ResultData updateSpreader(Integer userId, Integer spreaderID) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("spreaderID", spreaderID);
        int i = accountsInfoService.update(params);
        return i > 0 ? success() : error();
    }

    @GetMapping("{userId}")
    public ResultData findOne(@PathVariable("userId") Integer userId) {
        return success(accountsInfoService.findOne(userId));
    }
}
