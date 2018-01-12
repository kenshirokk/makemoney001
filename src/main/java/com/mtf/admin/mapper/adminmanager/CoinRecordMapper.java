package com.mtf.admin.mapper.adminmanager;

import com.mtf.admin.entity.CoinRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRecordMapper {

    /**
     * 保存金币充值记录
     * @param coinRecord
     * @return
     */
    int save(CoinRecord coinRecord);

    /**
     * 根据充值代理的id 取得他输出的总数
     * @param agencyId
     * @return
     */
    Long getQuantitySumByFromAgencyId(Integer agencyId);
}
