package com.mtf.admin.mapper.jeefwtwo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 查询返回实体类      用 find
 * 查询返回VO          用 get
 * 增加数据            用 save
 * 更新数据            用 update
 * 删除数据            用 delete
 */
@Repository
public interface SysUserMapper {

    List<Map<String, Object>> findAll();
}
