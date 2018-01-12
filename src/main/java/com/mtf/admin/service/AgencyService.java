package com.mtf.admin.service;

import com.mtf.admin.entity.Agency;

import java.util.List;
import java.util.Map;

public interface AgencyService {

    /**
     * 创建代理
     * @param agencyId
     * @param userId
     * @param password
     * @param phone
     * @return
     */
    int createAgency(Integer agencyId, Integer userId, String password, String phone);
    Agency findOneByLoginKey(String loginKey);
    Agency findByLogin(String loginKey,String loginPwd);

    /**
     * 超屌的递归查询
     * @param agencyId  指定查询谁的下级代理
     * @param level     指定递归深度 0: 自己, 1: 子级, 2: 子级 孙级
     * @param params    查询条件  and 关系
     *                      {
     *                          agencyId: 代理人id         精确匹配
     *                          nickname: 代理人昵称       模糊匹配
     *                          parentId: 父级(推荐人)     精确匹配
     *                      }
     * @return
     */
    List<Agency> findAll(Integer agencyId, Integer level, Map<String, Object> params);
}
