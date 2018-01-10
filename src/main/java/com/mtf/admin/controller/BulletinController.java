package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.vo.PageParam;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.Bulletin;
import com.mtf.admin.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bulletin")
public class BulletinController extends BaseController {

    @Autowired
    private BulletinService bulletinService;

    @GetMapping
    public ResultData list(PageParam page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Bulletin> list = bulletinService.findAll();
        PageInfo<Bulletin> pageInfo = new PageInfo<>(list);
        return success(list).set("total", pageInfo.getTotal());
    }

    @PostMapping("save")
    public ResultData save(Bulletin bulletin) {
        int i = bulletinService.save(bulletin);
        return i > 0 ? success() : error();
    }

    @PostMapping("update")
    public ResultData update(Bulletin bulletin) {
        int i = bulletinService.update(bulletin);
        return i > 0 ? success() : error();
    }

    @DeleteMapping("{id}")
    public ResultData delete(@PathVariable("id") Integer id) {
        int i = bulletinService.delete(id);
        return i > 0 ? success() : error();
    }
}
