package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mtf.admin.common.constant.Constant;
import com.mtf.admin.common.util.Cryptography;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.PageParam;
import com.mtf.admin.common.vo.PersonalInfoVO;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.entity.CoinRecord;
import com.mtf.admin.entity.RoomCardRecord;
import com.mtf.admin.service.AgencyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agency")
public class AgencyController extends BaseController {

    @Autowired
    private AgencyService agencyService;

    /**
     * 创建代理
     *
     * @return
     */
    @ApiOperation(value = "创建代理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agencyId", value = "当前登录用户id", required = true, paramType = "query", dataType =
                    "int"),
            @ApiImplicitParam(name = "userId", value = "玩家用户id", required = true, paramType = "query", dataType =
                    "int"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "phone", value = "电话号码", required = true, paramType = "query", dataType = "int")
    })
    @PostMapping("save")
    public ResultData createAgency(Integer agencyId, Integer userId, String password, String phone) {
        int i = agencyService.createAgency(agencyId, userId, password, phone);
        return i > 0 ? success() : error();
    }

    /**
     * 查询我的代理
     * 也就是parentId = 我 的那些人
     * 1: 超级管理员查询所有
     * 2: 超级代理 查询自己所有子孙
     * 3: 普通代理 查询自己2级子孙(子和孙)
     *
     * @return
     */
    @GetMapping
    public ResultData list(PageParam page, Integer agencyId, Integer parentId, String nickname) {
        Agency loginUser = super.getLoginUser();
        Integer level = null;
        if (Constant.AGENCY_TYPE_3.equals(loginUser.getAgencyType())) {
            level = 2;
        }

        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("parentId", parentId);
        params.put("nickname", nickname);

        PageHelper.startPage(page);
        List<Agency> list = agencyService.findAll(loginUser.getId(), level, params);
        PageInfo<Agency> pageInfo = new PageInfo<>(list);
        return success(list).set("total", pageInfo.getTotal());
    }

    @GetMapping("personalInfo")
    public ResultData personalInfo() {
        Agency loginUser = super.getLoginUser();
        PersonalInfoVO personalInfoVO = agencyService.personalInfo(loginUser.getId(), loginUser.getAgencyType());
        return success(personalInfoVO);
    }

    /**
     * 更新金币
     * @param agencyId        被更改代理id
     * @param quantity      充值金币数量
     * @return
     */
    @PostMapping("updateCoin")
    public ResultData updateCoin(Integer agencyId, Integer quantity) {
        Agency loginUser = super.getLoginUser();

        CoinRecord cr = new CoinRecord();
        cr.setFromAgencyId(loginUser.getId());
        cr.setRecord_type(Constant.RECORD_TYPE_AGENCY);
        cr.setToAgencyId(agencyId);
        cr.setQuantity(quantity);

        int i = agencyService.updateCoinPlus(cr);

        return i > 0 ? success() : error("您当前的金币不足");
    }

    /**
     * 更新房卡
     * @param agencyId
     * @param quantity
     * @return
     */
    @PostMapping("updateRoomCard")
    public ResultData updateRoomCard(Integer agencyId, Integer quantity) {
        Agency loginUser = super.getLoginUser();

        RoomCardRecord rcr = new RoomCardRecord();
        rcr.setFromAgencyId(loginUser.getId());
        rcr.setRecord_type(Constant.RECORD_TYPE_AGENCY);
        rcr.setToAgencyId(agencyId);
        rcr.setQuantity(quantity);

        int i = agencyService.updateRoomCardPlus(rcr);
        return i > 0 ? success() : error("您当前的房卡不足");
    }

    @PostMapping("updateAgencyType")
    public ResultData updateAgencyType(Integer agencyId, Integer agencyType) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("agencyType", agencyType);
        int i = agencyService.update(params);
        return i > 0 ? success() : error("更新失败");
    }

    @PostMapping("delete")
    public ResultData delete(Integer agencyId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("deleted", 1);
        int i = agencyService.update(params);
        return i > 0 ? success() : error("更新失败");
    }

    @PostMapping("toggleDisable")
    public ResultData toggleDisable(Integer agencyId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("disable", 666);
        int i = agencyService.update(params);
        return i > 0 ? success() : error("更新失败");
    }

    @PostMapping("updatePassword")
    public ResultData updatePassword(Integer agencyId, String password) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("password", Cryptography.md5(password));
        int i = agencyService.update(params);
        return i > 0 ? success() : error();
    }
}
