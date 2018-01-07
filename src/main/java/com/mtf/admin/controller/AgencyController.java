package com.mtf.admin.controller;

import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.Agency;
import com.mtf.admin.service.AgencyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agency")
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
            @ApiImplicitParam(name = "agencyId", value = "当前登录用户id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userId", value = "玩家用户id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "phone", value = "电话号码", required = true, paramType = "query", dataType = "int")
    })
    @PostMapping
    public ResultData createAgency(Integer agencyId, Integer userId, String password, String phone) {
        int i = agencyService.createAgency(agencyId, userId, password, phone);
        return i > 0 ? success() : error();
    }

    /**
     * 查询我的代理
     * 也就是parentId = 我 的那些人
     * @param id
     * @return
     */
    @GetMapping("myAgency/{id}")
    public ResultData myAgency(@PathVariable("id") Integer id) {
        List<Agency> list = agencyService.findByParentId(id);
        return success(list);
    }
}
