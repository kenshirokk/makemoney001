package com.mtf.admin.mapper.qpaccountsdb;

import com.mtf.admin.common.vo.AccountsInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountsInfoMapper {

    /**
     * 查询所有玩家
     */
    List<AccountsInfoVO> findAll(@Param("agencyId") Integer agencyId,
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
    AccountsInfoVO findOne(Integer userId);

    /**
     * 根据用户id 增加金币
     * @param userId
     * @param quantity
     * @return
     */
    int updateCoinPlus(@Param("userId") Integer userId, @Param("quantity") Integer quantity);

    /**
     * 根据用户id 增加房卡
     * @param userId
     * @param quantity
     * @return
     */
    int updateRoomCardPlus(@Param("userId") Integer userId, @Param("quantity") Integer quantity);
}
