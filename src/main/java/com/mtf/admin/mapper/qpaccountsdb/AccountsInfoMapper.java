package com.mtf.admin.mapper.qpaccountsdb;

import com.mtf.admin.entity.AccountsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountsInfoMapper {

    /**
     * 查询所有玩家
     */
    List<AccountsInfo> findAll(@Param("agencyId") Integer agencyId,
                               @Param("level") Integer level,
                               @Param("params") Map<String, Object> params);

    /**
     * 更新操作
     * 金币/房卡  累加
     */
    int updatePlus(Map<String, Object> params);

    /**
     * 就是更新操作
     */
    int update(Map<String, Object> params);

    /**
     * 根据用户id 查询用户
     * @param userId
     * @return
     */
    AccountsInfo findOne(Integer userId);
}
