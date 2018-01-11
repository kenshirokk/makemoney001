package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.PageParam;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.Enchashment;
import com.mtf.admin.service.EnchashmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("enchashment")
public class EnchashmentController extends BaseController {

    @Autowired
    private EnchashmentService enchashmentService;

    /**
     * 管理员查询所有
     * @param agencyId
     * @param agencyNickname
     * @param page
     * @return
     */
    @GetMapping
    public ResultData list(Integer agencyId, String agencyNickname, PageParam page) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("agencyId", agencyId);
        params.put("agencyNickname", agencyNickname);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Enchashment> list = enchashmentService.findAll(params);
        PageInfo<Enchashment> pageInfo = new PageInfo<>(list);
        return success(list).set("total", pageInfo.getTotal());
    }

    /**
     * 提交一个提现申请
     * @param enchashment
     * @return
     */
    @PostMapping("save")
    public ResultData save(Enchashment enchashment) {
        int i = enchashmentService.save(enchashment);
        return i > 0 ? success() : error();
    }

    /**
     * 更新提现申请,(status字段)
     * @param enchashment
     * @return
     */
    @PostMapping("update")
    public ResultData update(Enchashment enchashment) {
        int i = enchashmentService.update(enchashment);
        return i > 0 ? success() : error();
    }
}
