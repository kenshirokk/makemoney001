package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.RoomCardRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCardRecordMapper {

    /**
     * 保存房卡充值记录
     * @param roomCardRecord
     * @return
     */
    int save(RoomCardRecord roomCardRecord);

    /**
     * 根据充值代理的id 取得他输出的总数
     * @param agencyId
     * @return
     */
    Long getQuantitySumByFromAgencyId(Integer agencyId);
}
