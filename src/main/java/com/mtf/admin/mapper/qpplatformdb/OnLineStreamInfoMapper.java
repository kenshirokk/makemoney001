package com.mtf.admin.mapper.qpplatformdb;

import org.springframework.stereotype.Repository;

@Repository
public interface OnLineStreamInfoMapper {

    /**
     * 获取最新的 在线人数
     * @return
     */
    Integer getOnlineUser();
}
