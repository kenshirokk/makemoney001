package com.mtf.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtf.admin.common.util.Cryptography;
import com.mtf.admin.common.vo.BaseController;
import com.mtf.admin.common.dto.BulletinDTO;
import com.mtf.admin.common.vo.PageParam;
import com.mtf.admin.common.vo.ResultData;
import com.mtf.admin.entity.Bulletin;
import com.mtf.admin.service.BulletinService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/bulletin")
public class BulletinController extends BaseController {

    @Value("${config.uploadPath}")
    private String uploadPath;

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
    public ResultData save(BulletinDTO dto){
        Bulletin b = dto.getBulletin();
        int i = bulletinService.save(b);
        return i > 0 ? success() : error();
    }

    @PostMapping("update")
    public ResultData update(BulletinDTO dto) throws IOException  {
        Bulletin b = dto.getBulletin();
        if (dto.getFile() != null) {
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = Cryptography.md5(b.getTitle() + System.currentTimeMillis()) + ".jpg";
            Path path = Paths.get(uploadPath + fileName);
            Files.write(path, dto.getFile().getBytes());
            b.setImage(fileName);
        }
        if(StringUtils.isBlank(b.getImage())){
            b.setImage(null);
        }
        int i = bulletinService.update(b);
        return i > 0 ? success(b) : error();
    }

    @DeleteMapping("{id}")
    public ResultData delete(@PathVariable("id") Integer id) {
        int i = bulletinService.delete(id);
        return i > 0 ? success() : error();
    }
}
