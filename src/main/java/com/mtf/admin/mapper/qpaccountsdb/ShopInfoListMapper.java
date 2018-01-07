package com.mtf.admin.mapper.qpaccountsdb;

import com.mtf.admin.entity.ShopInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 商城管理
 * 商品配置表
 */
@Repository
public interface ShopInfoListMapper {

    /**
     * 查询所有商品
     * @return
     */
    List<ShopInfo> findAll();

    /**
     * 根据商品itemId 修改商品价格 & 商品数量
     * @param params
     * @return
     */
    int update(Map<String, Object> params);

}
