package com.mtf.admin.mapper.qpaccountsdb;

import com.mtf.admin.entity.AccountsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountsInfoMapper {

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
