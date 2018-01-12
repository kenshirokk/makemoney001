package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mtf.admin.common.constant.Constant;
import com.mtf.admin.common.vo.AccountsInfoVO;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.PageParam;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.entity.CoinRecord;
import com.mtf.admin.entity.RoomCardRecord;
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
    public ResultData list(PageParam page, Integer userId, Integer spreaderID, String nickname) {
        Agency loginUser = getLoginUser();
        Integer level = null;
        if (Constant.AGENCY_TYPE_3.equals(loginUser.getAgencyType())) {
            level = 2;
        }

        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("spreaderId", spreaderID);
        params.put("nickname", nickname);

        PageHelper.startPage(page);
        List<AccountsInfoVO> list = accountsInfoService.findAll(loginUser.getId(), level, params);
        PageInfo<AccountsInfoVO> pageInfo = new PageInfo<>(list);
        return success(list).set("total", pageInfo.getTotal());
    }

    /**
     * 更新金币
     * @param userId        被更改用户id
     * @param quantity      充值金币数量
     * @return
     */
    @PostMapping("updateCoin")
    public ResultData updateCoin(Integer userId, Integer quantity) {
        Agency loginUser = super.getLoginUser();

        CoinRecord cr = new CoinRecord();
        cr.setFromAgencyId(loginUser.getId());
        cr.setRecord_type(Constant.RECORD_TYPE_USER);
        cr.setToUserId(userId);
        cr.setQuantity(quantity);

        int i = accountsInfoService.updateCoinPlus(cr);

        return i > 2 ? success() : error();
    }

    /**
     * 更新房卡
     * @param userId
     * @param quantity
     * @return
     */
    @PostMapping("updateRoomCard")
    public ResultData updateRoomCard(Integer userId, Integer quantity) {
        Agency loginUser = super.getLoginUser();

        RoomCardRecord rcr = new RoomCardRecord();
        rcr.setFromAgencyId(loginUser.getId());
        rcr.setRecord_type(Constant.RECORD_TYPE_USER);
        rcr.setToUserId(userId);
        rcr.setQuantity(quantity);

        int i = accountsInfoService.updateRoomCardPlus(rcr);
        return i > 2 ? success() : error();
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
