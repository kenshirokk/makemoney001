package com.mtf.admin.service;

import com.mtf.admin.common.vo.AccountsInfoVO;
import com.mtf.admin.entity.CoinRecord;
import com.mtf.admin.entity.RoomCardRecord;

import java.util.List;
import java.util.Map;

public interface AccountsInfoService {

    /**
     * 查询所有玩家
     */
    List<AccountsInfoVO> findAll(Integer agencyId, Integer level, Map<String, Object> params);

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
     * 增加金币
     * @param coinRecord
     * @return
     */
    int updateCoinPlus(CoinRecord coinRecord);

    /**
     * 增加房卡
     * @param roomCardRecord
     * @return
     */
    int updateRoomCardPlus(RoomCardRecord roomCardRecord);
}
