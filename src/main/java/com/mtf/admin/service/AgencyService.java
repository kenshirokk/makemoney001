package com.mtf.admin.service;

import com.mtf.admin.entity.Agency;

import java.util.List;

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
     * 根据parentId 查询 代理列表
     * @param parentId
     * @return
     */
    List<Agency> findByParentId(Integer parentId);
}
