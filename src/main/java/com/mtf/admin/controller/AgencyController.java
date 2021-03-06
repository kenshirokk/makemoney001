package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.zxing.WriterException;
import com.mtf.admin.common.annotation.AdminMethod;
import com.mtf.admin.common.constant.Constant;
import com.mtf.admin.common.util.Cryptography;
import com.mtf.admin.common.vo.*;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.entity.CoinRecord;
import com.mtf.admin.entity.RoomCardRecord;
import com.mtf.admin.service.AgencyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
            @ApiImplicitParam(name = "userId", value = "玩家用户id", required = true, paramType = "query", dataType =
                    "int"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "phone", value = "电话号码", required = true, paramType = "query", dataType = "int")
    })
    @PostMapping("save")
    public ResultData createAgency(Integer userId, String password, String phone) throws IOException, WriterException {
        synchronized ("createAgency_" + userId) {
            Agency valiAgency = agencyService.finByUserId(userId);
            if (valiAgency != null) {
                return error("这个代理已经存在");
            }
            int i = agencyService.createAgency(super.getLoginUser().getId(), userId, password, phone);
            return i > 0 ? success() : error();
        }
    }

    @GetMapping("findByUserId/{userId}")
    public ResultData findByUserId(@PathVariable("userId") Integer userId) {
        return success(agencyService.findOne(userId));
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
    public ResultData list(PageParam page, Integer agencyId, Integer parentId, String nickname,
                           Boolean onlyChild) {
        Agency loginUser = super.getLoginUser();
        Integer level = null;
        if (Constant.AGENCY_TYPE_3.equals(loginUser.getAgencyType())) {
            level = 2;
        }

        if (onlyChild != null && onlyChild) {
            level = 1;
        }

        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("parentId", parentId);
        params.put("nickname", nickname);

        PageHelper.startPage(page);
        List<Agency> list = agencyService.findAll(loginUser.getId(), level, params);
        for(Agency a : list){
            a.setAgencyBalance(a.getAgencyBalance()/100);
        }
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
     *
     * @param agencyId 被更改代理id
     * @param quantity 充值金币数量
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
     *
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
    @AdminMethod
    public ResultData delete(Integer agencyId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("deleted", 1);
        int i = agencyService.update(params);
        return i > 0 ? success() : error("更新失败");
    }

    @PostMapping("toggleDisable")
    @AdminMethod
    public ResultData toggleDisable(Integer agencyId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("disable", 666);
        int i = agencyService.update(params);
        return i > 0 ? success() : error("更新失败");
    }

    @PostMapping("updatePassword")
    public ResultData updatePassword(Integer agencyId, String password) {
        Agency loginUser = getLoginUser();
        agencyId = agencyId == null ? loginUser.getId() : agencyId;
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("password", Cryptography.md5(password));
        List<Agency> list = agencyService.findAll(loginUser.getId(),null,params);
        if((list == null || list.size() ==  0) && !agencyId.equals(loginUser.getId())){
            return  error();
        }
        int i = agencyService.update(params);
        return i > 0 ? success() : error();
    }

    @GetMapping("getMoneyFlowVO")
    public ResultData getMoneyFlowVO(Integer year) {
        Agency loginUser = getLoginUser();
        List<MoneyFlowVO> moneyFlowVO = agencyService.getMoneyFlowVO(loginUser.getId(), year);
        return success(moneyFlowVO);
    }

    @GetMapping("getMoneyFlowVODetail")
    public ResultData getMoneyFlowVODetail(PageParam page, Integer year, Integer month, Integer UserId) {
        Agency loginUser = getLoginUser();
        PageInfo<SellRecordVO> pageInfo = PageHelper.startPage(page).doSelectPageInfo(() -> agencyService
                .getMoneyFlowVODetail(year, month, loginUser.getId(), UserId));
        return success(pageInfo.getList()).set("total", pageInfo.getTotal());
    }

    @GetMapping("getSellRecordVO")
    public ResultData getSellRecordVO(PageParam page, Integer directAgencyId, Integer directPlayerId) {
        Agency loginUser = getLoginUser();
        PageInfo<SellRecordVO> pageInfo = PageHelper.startPage(page).doSelectPageInfo(() -> agencyService
                .getSellRecordVO(loginUser.getId(), directAgencyId,directPlayerId));
        return success(pageInfo.getList()).set("total", pageInfo.getTotal());
    }

    @GetMapping("getPerformanceVO")
    public ResultData getPerformanceVO(Integer year) {
        Agency loginUser = getLoginUser();
        List<PerformanceVO> performanceVO = agencyService.getPerformanceVO(loginUser.getId(), year);
        List<PerformanceDoubleVO> list = Lists.newArrayList();
        for(PerformanceVO vo : performanceVO){
            PerformanceDoubleVO dvo = new PerformanceDoubleVO();
            dvo.setMonth(vo.getMonth());
            dvo.setLevelOneMoney(vo.getLevelOneMoney() / 100.0);
            dvo.setLevelTwoMoney(vo.getLevelTwoMoney() / 100.0);
            list.add(dvo);
        }
        return success(list);
    }

    @GetMapping("getPerformanceVODetail")
    public ResultData getPerformanceVODetail(PageParam page, Integer year, Integer month, Integer userId) {
        Agency loginUser = getLoginUser();
        PageInfo<PerformanceDetailVO> pageInfo = PageHelper.startPage(page).doSelectPageInfo(() -> agencyService
                .getPerformanceVODetail(year, month, loginUser.getId(), userId));
        return success(pageInfo.getList()).set("total", pageInfo.getTotal());
    }

}
