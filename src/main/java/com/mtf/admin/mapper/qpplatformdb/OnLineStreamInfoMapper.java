package com.mtf.admin.mapper.qpplatformdb;

import com.mtf.admin.entity.OnLineStreamInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface OnLineStreamInfoMapper {

    OnLineStreamInfo findLimitOne();
}
