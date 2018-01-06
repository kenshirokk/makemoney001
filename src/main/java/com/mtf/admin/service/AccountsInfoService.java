package com.mtf.admin.service;

import com.mtf.admin.entity.AccountsInfo;

import java.util.List;
import java.util.Map;

public interface AccountsInfoService {

    /**
     * 查询所有玩家
     */
    List<AccountsInfo> findAll();

    /**
     * 更新操作
     * 金币/房卡  累加
     */
    int updatePlus(Map<String, Object> params);

    /**
     * 就是更新操作
     */
    int update(Map<String, Object> params);
}
