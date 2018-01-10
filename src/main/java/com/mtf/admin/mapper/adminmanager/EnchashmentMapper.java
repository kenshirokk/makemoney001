package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.Enchashment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnchashmentMapper {

    /**
     *查询所有 带条件
     * @return
     */
    List<Enchashment> findAll(Map<String, Object> params);

    /**
     * 提交提现申请
     * @param enchashment
     * @return
     */
    int save(Enchashment enchashment);

    /**
     * 更新提现申请--(审批)
     * @param enchashment
     * @return
     */
    int update(Enchashment enchashment);
}
